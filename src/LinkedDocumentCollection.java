/**
 * A Collection of {@link LinkedDocument}s.
 * 
 * This class ensures, that only {@link LinkedDocument}s are added to this
 * collection.
 * 
 * @author
 *
 */
public class LinkedDocumentCollection extends DocumentCollection {

  /**
   * Empty constructor that just utilizes constructor of super class.
   */
  public LinkedDocumentCollection() {
    super();
  }

  /**
   * The specified {@link Document} is added to this collection only if it is of
   * type {@link LinkedDocument} and if it is not already contained.
   * 
   * @param doc the document to add
   */
  public void prependDocument(LinkedDocument doc) {
    if (!(this.contains(doc))) {
      super.prependDocument(doc);
    }
  }

  /**
   * The specified {@link Document} is added to this collection only if it is of
   * type {@link LinkedDocument} and if it is not already contained.
   * 
   * @param doc the document to add
   */
  @Override
  public void appendDocument(Document doc) {
    if ((doc instanceof LinkedDocument) && !(this.contains(doc))) {
      super.appendDocument(doc);
    }
  }

  /**
   * Private helper method that crawls this collection.
   * 
   * This method adds all LinkedDocuments of this collection to the specified
   * {@link LinkedDocumentCollection}, if they are not already contained.
   * Additionally, this method recursively crawls all outgoing links of all
   * {@link LinkedDocument}s of this collection.
   * 
   * @param resultCollection in and out parameter. All LinkedDocuments are
   *                         (recursively) added to this
   *                         {@link LinkedDocumentCollection}.
   */
  private void crawl(LinkedDocumentCollection resultCollection) {
    if (this.numDocuments() == 0) {
      return;
    }

    /*
     * loop over all documents of this collection and add them to the in/out
     * parameter, if not already contained.
     */
    for (int i = 0; i < this.numDocuments(); i++) {
      LinkedDocument doc = (LinkedDocument) this.get(i);

      if (!resultCollection.contains(doc)) {
        resultCollection.appendDocument(doc);

        /* do the same recursively */
        doc.getOutgoingLinks(resultCollection).crawl(resultCollection);
      }
    }
  }

  /**
   * This method crawls this {@link LinkedDocumentCollection} and returns a new
   * {@link LinkedDocumentCollection}.
   * 
   * The returned LinkedDocumentCollection contains all LinkedDocuments of this
   * LinkedDocumentCollection plus any LinkedDocuments that the LinkedDocuments of
   * this collection link to. If these additional LinkedDocuments again link to
   * other LinkedDocuments they will be included as well, and so on.
   * 
   * @return a {@link LinkedDocumentCollection} that contains all
   *         {@link LinkedDocument}s of this collection plus any
   *         {@link LinkedDocument}s that are linked either directly or
   *         indirectly.
   */
  public LinkedDocumentCollection crawl() {
    /* prepare the resulting collection and begin crawling ... */
    LinkedDocumentCollection resultCollection = new LinkedDocumentCollection();
    this.crawl(resultCollection);
    return resultCollection;
  }

  /**
   * This method calculates all incoming links for every {@link LinkedDocument} in
   * this collection. The incoming links are assigned to every LinkedDocument via
   * the {@link LinkedDocument#addIncomingLink(LinkedDocument)} method.
   */
  public void calculateIncomingLinks() {
    /* loop over all documents in this collection */
    for (int i = 0; i < this.numDocuments(); i++) {
      LinkedDocument doc = (LinkedDocument) this.get(i);

      /* again, loop over all documents of this collection */
      for (int j = 0; j < this.numDocuments(); j++) {
        LinkedDocument incomingDoc = (LinkedDocument) this.get(j);

        /*
         * Check if doc is contained in the outgoing links of incomingDoc. If so, add to
         * incoming links of doc.
         */
        if (incomingDoc.getOutgoingLinks().contains(doc)) {
          doc.addIncomingLink(incomingDoc);
        }
      }
    }
  }

  /**
   * Returns a string representation of this {@link LinkedDocumentCollection}
   * using the IDs of the {@link LinkedDocument}s.
   * 
   * @return a string representation of this collection
   */
  public String toString() {
    if (this.numDocuments() == 0) {
      return "[]";
    }

    if (this.numDocuments() == 1) {
      return "[" + ((LinkedDocument) this.get(0)).getID() + "]";
    }

    String res = "[";
    for (int i = 0; i < this.numDocuments() - 1; i++) {
      res += ((LinkedDocument) this.get(i)).getID() + ", ";
    }
    res += ((LinkedDocument) this.get(this.numDocuments() - 1)).getID() + "]";
    return res;
  }

  /**
   * Finds a {@link LinkedDocument} with the given id 
   * if contained in this {@link LinkedDocumentCollection}
   * 
   * @param id           the id to be looked up
   * @return the found {@link LinkedDocument} or null
   */
  public LinkedDocument findByID(String id) {
    for (int i = 0; i < this.numDocuments(); i++)
      if (((LinkedDocument) this.get(i)).getID().equals(id))
        return ((LinkedDocument) this.get(i));
    return null;
  }
  public double[] pageRankRec(double d)
  {
    int depth = 30;
    LinkedDocumentCollection dc = crawl();
    if (dc.isEmpty()) return null;
    int[][] C = new int[dc.numDocuments()][dc.numDocuments()];
    for(int i = 0; i < dc.numDocuments(); i++)
    {
      for (int k = 0; k < dc.numDocuments(); k++){
        if (((LinkedDocument)dc.get(i)).getOutgoingLinks().contains(dc.get(k)) || ((LinkedDocument)dc.get(i)).getOutgoingLinks().isEmpty()) {
          C[i][k] = 1;
        }
        else
          C[i][k] = 0;
      }
    }
    double[] result = new double[dc.numDocuments()];
    for(int i = 0; i < dc.numDocuments(); i++){
      result[i] = pageRankRec(C,  i, d, depth);
    }
    return result;
  }
  public double pageRankRec(int[][] C, int i, double d, int depth){
    int length = C.length;
    double PageRankRatioSum = 0;
    for (int j = 0; j < length; j++) {
      if (C[j][i] == 1) {  // If there is i in j
        double pageRankForJ;
        if (depth == 0)
          pageRankForJ = 1 / length;
        else
          pageRankForJ = pageRankRec(C, j, d, depth - 1);
        int sumOfPageRanks = 0 ;
        for (int k = 0; k < length; k++ ){
          sumOfPageRanks += C[j][k];
        }
        PageRankRatioSum += pageRankForJ / sumOfPageRanks;
      }
    }
    return (1 - d) / length + d * PageRankRatioSum;
  }

  public double[] pageRank(double dampingFactor){
    return null;
  }
  public static double dotProduct(double[] vector1, double[] vector2) {
    double sum = 0;
    int n = vector1.length;
    for (int i = 0; i < n; i++)
      sum += vector1[i] * vector2[i];
    return sum;
  }

  private static double[] multiply(double[][] matrix, double[] vector){
    int n = matrix.length;
    double[] resultVector = new double[n];
    for (int i = 0; i < n; i++)
      resultVector[i] = dotProduct(matrix[i], vector);
    return resultVector;

  }


  private double[] sortByRelevance(double dampingFactor, double weightingFactor){
    int n = numDocuments();
    double[] relevance = new double[n];
    for (int i = 0; i < n ; i++) {
      relevance[i] = weightingFactor * this.getQuerySimilarity(i) + (1.0 - weightingFactor) * pageRankRec(dampingFactor)[i];
    }
    Object[] result = mergeSortIt(relevance);
    Document[] resultDc = (Document[]) result[1];
    double[] resultRank = new double[n];
    for (int i = 0 ; i < n; i++ ) {
      this.removeFirstDocument();
      resultRank[i] = ((double[]) result[0])[n - 1 - i];
    }
    for (int i = 0; i < n; i++ ) {
      this.prependDocument(resultDc[i]);
    }
    return resultRank;
  }
  private Object[] merge(double[] a, double[] b, Document[] aDc, Document[] bDc) {
    double [] merged = new double[a.length + b.length];
    Document[] mergedDc = new Document[a.length + b.length];
    int aIndex = 0;
    int bIndex = 0;
    for (int i = 0; i < merged.length; i++) {
      if (aIndex >= a.length) {
        merged[i] = b[bIndex];
        mergedDc[i] = bDc[bIndex++];
      } else if (bIndex >= b.length) {
        merged[i] = a[aIndex];
        mergedDc[i] = aDc[aIndex++];
      } else if (a[aIndex] < b[bIndex]) {
        merged[i] = a[aIndex];
        mergedDc[i] = aDc[aIndex++];
      } else {
        merged[i] = b[bIndex];
        mergedDc[i] = bDc[bIndex++];
      }
    }
    Object[] result = {merged, mergedDc};
    return result;
  }
  private Object[] mergeSortIt(double[] a) {
    //dividing array in a.length onelement arrays
    double[][] partitions = new double[a.length][];
    Document partitionDocs[][]  = new Document[a.length][];
    for (int i = 0; i < a.length; i++) {
      partitions[i] = new double[]{a[i]};
      partitionDocs[i] = new Document[]{this.get(i)};
    }
    // merging neighboring arrays
    while (partitions.length > 1) {
      double[][] partitionsNew = new double[(partitions.length + 1) / 2][];
      Document[][] partitionsNewDocs  = new Document[(partitions.length + 1) / 2][];
      for (int i = 0; i < partitionsNew.length; i++) {
        if (2 * i + 1 < partitions.length) {
          Object[] partitionsNewObj = merge(partitions[2 * i], partitions[2 * i + 1], partitionDocs[2 * i], partitionDocs[2 * i + 1]);
          partitionsNew[i] = (double[]) partitionsNewObj[0];
          partitionsNewDocs[i] = (Document[]) partitionsNewObj[1];
        }
        else {
          // if length not divisible by 2, just take last
          partitionsNew[i] = partitions[2 * i];
          partitionsNewDocs[i] = partitionDocs[2 * i];
        }
      }
      partitions = partitionsNew;
      partitionDocs = partitionsNewDocs;
    }
    Object[] result = {partitions[0], partitionDocs[0]};
    return result;
  }



  public double[] matchAndSortByRelevance(String searchQuery) {
    if (this.isEmpty())
    {
      return null;
    }
    if (searchQuery == null || searchQuery.equals(""))
    {
      return null;
    }
    LinkedDocument givenDoc = new LinkedDocument("", "", "",
            null, null, searchQuery, "Dummy");
    this.prependDocument(givenDoc);
    this.addZeroWordsToDocuments();
    DocumentCollectionCell temp = this.first;
    while (temp != null) {
      temp.getDocument().getWordCounts().sort();
      temp = temp.getNext();
    }
    //calculate similarities with the given doc
    temp = this.first.getNext();
    while (temp != null) {
      temp.setQuerySimilarity(temp.getDocument().getWordCounts().computeSimilarity(givenDoc.getWordCounts()));
      temp = temp.getNext();
    }

    //remove the givendoc
    this.removeFirstDocument();

    return this.sortByRelevance(0.80, 0.6);
  }



}
