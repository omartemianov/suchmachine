/**
 * TestIt-Klasse.
 */
public class TestIt {
  /**
   * Main-Methode
   *
   * @param args Kommandozeilen-Argumente
   */
  public static void main(String[] args) {

    LinkedDocumentCollection ldc = new LinkedDocumentCollection();
    String command;

    boolean exit = false;

        while (!exit) {
          command = Terminal.askString("> ");

          if (command == null || command.equals("exit")) {
            /* Exit program */
            exit = true;
          } else if (command.startsWith("add ")) {
            /* add a new document */
            String titleAndText = command.substring(4);

            /* title and text separated by : */
            int separator = titleAndText.indexOf(':');
            String title = titleAndText.substring(0, separator);
            String text = titleAndText.substring(separator + 1);

            ldc.appendDocument(new LinkedDocument(title, "", "", null, null, text, title));
          } else if (command.startsWith("list")) {
            /* list all document in collection */
            for (int i = 0; i < ldc.numDocuments(); i++) {
              System.out.println(ldc.get(i).getTitle());
            }
          } else if (command.startsWith("pageRank")) {
            double[] rank = ldc.pageRankRec(0.85);
            for (int i = 0; i < ldc.numDocuments(); i++) {
              System.out.println(ldc.get(i).getTitle() + "; PageRank: " + rank[i]);
            }
          } else if (command.startsWith("query ")) {
            /* query on the documents in the collection */
            String query = command.substring(6);

            double[] result = ldc.matchAndSortByRelevance(query);

            for (int i = 0; i < ldc.numDocuments(); i++) {
              System.out.println((i + 1) + ". " + ldc.get(i).getTitle() + "; Relevance " + result[i]);
            }

            System.out.println();

          } else if (command.startsWith("count ")) {
            /* print the count of a word in each document */
            String word = command.substring(6);

            for (int i = 0; i < ldc.numDocuments(); i++) {
              Document doc = ldc.get(i);
              WordCountsArray docWordCounts = doc.getWordCounts();

              int count = docWordCounts.getCount(docWordCounts.getIndexOfWord(word));

              /* -1 and 0 makes a difference! */
              if (count == -1) {
                System.out.println(doc.getTitle() + ": gar nicht.");
              } else {
                System.out.println(doc.getTitle() + ": " + count + "x ");
              }
            }
          } else if (command.startsWith("crawl")) {
            ldc = ldc.crawl();
          }
        }

      }

  }

/*
Seien die Dateien a.txt, c.txt, d.txt, e.txt wie folgt gegeben:
a.txt
es war einmal link:b.txt link:c.txt
c.txt
once upon a time link:d.txt
d.txt
 erase una vez link:c.txt e.txt c era una volta link:b.txt

 Die Ausführung der main-Methode in der Klasse TestIt sieht dann wie folgt aus:

> add b.txt:link:a.txt link:e.txt
> crawl
> pageRank
b.txt; PageRank: 0.14896467517519874
a.txt; PageRank: 0.09330868678914084
c.txt; PageRank: 0.3396315535187274
d.txt ; PageRank: 0.3183002520722213
e.txt; PageRank: 0.09330868678914084
> query einmal
1. a.txt; Relevance 0.3894866366527345
2. c.txt; Relevance 0.12766163104667325
3. d.txt ; Relevance 0.118097825324825
4. b.txt; Relevance 0.06769145253801255
5. e.txt; Relevance 0.043076475138958945

Seien die Dateien Hello, Hello2, Hello3, Hello4 wie folgt gegeben:
hello.txt
link:hello1.txt link:hello3.txt

hello2.txt
link:hello1.txt helooooo link:hello.txt

hello3.txt
link:hello.txt some dummy text herelink:hello2.txt link:hello1.txt

hello4.txt
link:hello.txt was going to say hello to link:hello1.txt and link:hello2.txt link:hello3.txt


Die Ausführung der main-Methode in der Klasse TestIt sieht dann wie folgt aus:
> add hello1.txt:link:hello2.txt
> crawl
> pageRank
hello1.txt; PageRank: 0.29923072918003235
hello2.txt; PageRank: 0.3575523959843644
hello3.txt; PageRank: 0.23322511072275645
hello.txt; PageRank: 0.10350561845727589
> query hello1
1. hello2.txt; Relevance 0.14068680042886492
2. hello1.txt; Relevance 0.11945852937928188
3. hello3.txt; Relevance 0.09431417604732424
4. hello.txt; Relevance 0.04514435333195763
*/

