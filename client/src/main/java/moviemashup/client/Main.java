package moviemashup.client;

import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {
        ClientWrapper clientWrapper = new ClientWrapper();

        Options options = new Options();

        Option addOption = Option.builder("a")
                .longOpt("add")
                .hasArgs()
                .numberOfArgs(4)
                .desc("Ajouter un film")
                .argName("titre> <annee> <date_de_visionnage> <note")
                .build();

        options.addOption(addOption);

        options.addOption(Option.builder("h")
                .longOpt("help").
                desc("Afficher l'aide")
                .build());

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h") || args.length == 0) {
                formatter.printHelp("java -jar client.jar", options);
                return;
            }

            if (cmd.hasOption("a")) {
                String[] movieArgs = cmd.getOptionValues("a");

                String titre = movieArgs[0];
                short year = Short.parseShort(movieArgs[1]);
                String visualisationDate = movieArgs[2];
                short points = Short.parseShort(movieArgs[3]);
                clientWrapper.addMovie(titre, year, visualisationDate, points);
                System.out.println("Votre film "+ titre +" a été ajouté !");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
