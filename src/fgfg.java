public class fgfg {
    public static  void main(String[] args)
    {


        LinkedDocumentCollection toTest = new LinkedDocumentCollection();
        LinkedDocument doc = LinkedDocument.createLinkedDocumentFromFile("B");
        System.out.println(doc.getTitle() + " " + doc.getID() + " ");
        System.out.println();
        toTest.appendDocument(doc);

        toTest = toTest.crawl();
        toTest.calculateIncomingLinks();
        for (int i = 0; i < toTest.numDocuments(); i++) {
            System.out.println(toTest.get(i).getTitle() + " " + ((LinkedDocument) toTest.get(i)).getID());
            LinkedDocumentCollection ldc = ((LinkedDocument) toTest.get(i)).getIncomingLinks();
            if (ldc != null && ldc.get(0) != null)
                System.out.print(ldc.get(0).getTitle());
            if (ldc != null && ldc.get(1) != null) {
                System.out.print(ldc.get(1).getTitle());
                System.out.print(" " + ldc.get(0).getWordCounts().computeSimilarity(ldc.get(0).getWordCounts(), toTest));
                System.out.print(" " + ldc.get(0).getWordCounts().computeSimilarity(ldc.get(1).getWordCounts(), toTest));
                System.out.println();

            }
        }
    }

}

