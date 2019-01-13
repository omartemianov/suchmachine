public class TestRank {
    public static void main(String[] args)
    {
        LinkedDocumentCollection a = new LinkedDocumentCollection();
        a.appendDocument(LinkedDocument.createLinkedDocumentFromFile("A"));

        double[] q = a.pageRankRec(0.85);
        for (double y : q) {
           System.out.print(y + " ");
        }
        System.out.println();

    }
}
