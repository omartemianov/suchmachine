import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestItServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);




        boolean exit = false;
        //try {
           // while (true) {
                LinkedDocumentCollection ldc = new LinkedDocumentCollection();
                String command;
                Socket client = serverSocket.accept();



                while (!exit) {
                    PrintWriter socketWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    socketWriter.

                    socketWriter.println(">");
                    socketWriter.flush();

                        command = bufferedReader.readLine();


                        if (command.equals("exit")) {
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
                                socketWriter.println(ldc.get(i).getTitle());

                            }
                            socketWriter.flush();
                        } else if (command.startsWith("pageRank")) {
                            double[] rank = ldc.pageRankRec(0.85);
                            for (int i = 0; i < ldc.numDocuments(); i++) {
                                socketWriter.println(ldc.get(i).getTitle() + "; PageRank: " + rank[i]);
                                socketWriter.flush();
                            }
                        } else if (command.startsWith("query ")) {
                            /* query on the documents in the collection */
                            String query = command.substring(6);

                            double[] result = ldc.matchAndSortByRelevance(query);

                            for (int i = 0; i < ldc.numDocuments(); i++) {
                                socketWriter.println((i + 1) + ". " + ldc.get(i).getTitle() + "; Relevance " + result[i]);

                            }


                            socketWriter.println();
                            socketWriter.flush();

                        } else if (command.startsWith("count ")) {
                            /* print the count of a word in each document */
                            String word = command.substring(6);

                            for (int i = 0; i < ldc.numDocuments(); i++) {
                                Document doc = ldc.get(i);
                                WordCountsArray docWordCounts = doc.getWordCounts();

                                int count = docWordCounts.getCount(docWordCounts.getIndexOfWord(word));

                                /* -1 and 0 makes a difference! */
                                if (count == -1) {
                                    socketWriter.println(doc.getTitle() + ": gar nicht.");
                                    socketWriter.flush();
                                } else {
                                    socketWriter.println(doc.getTitle() + ": " + count + "x ");
                                    socketWriter.flush();
                                }
                            }
                        } else if (command.startsWith("crawl")) {
                            ldc = ldc.crawl();
                        }

                }
                client.close();


       // } finally {
         //   serverSocket.close();
        //}
    }
}
