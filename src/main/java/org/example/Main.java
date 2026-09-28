package org.example;

import moviemashup.model.moviemodel.MovieModel;
import moviemashup.model.moviemodel.MovieModelFactory;
import moviemashup.thrift.MovieDto;
import moviemashup.thrift.MovieService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

    }
//    public static void main(String[] args) {
//        try {
//            System.out.println("OK1");
//            THttpClient transport = new THttpClient("http://localhost:8080/thrift/thrift/movie");
//            // MovieService.Client client = new MovieService.Client(new TBinaryProtocol(transport));
//            MovieService.Client client = new MovieService.Client.Factory().getClient(new TBinaryProtocol(transport));
//
//            System.out.println("=== Add movie ===");
//            MovieDto dto = new MovieDto("Un sacré bon film", (short) 2026, "2026-09-09", (short) 0);
//            client.addMovie(dto);
//
//            MovieDto res = client.findMovieByTitle("Un sacré bon film");
//            System.out.println("Movie :\n" + res.getTitle() + "\n" + res.getYear() + "\n"+ res.getVisualisationDate() + "\n"+ res.getPoints() + "\n");
//
//
//            System.out.println("=== Find by year ===");
//            List<MovieDto> movies = client.findMoviesByYear((short) 2001);
//            for(MovieDto m : movies){
//                System.out.println("Movie :\n" + m.getTitle() + "\n" + m.getYear() + "\n"+ m.getVisualisationDate() + "\n"+ m.getPoints() + "\n");
//            }
//
//            System.out.println("=== Find by title ===");
//            MovieDto m2 = client.findMovieByTitle("The Shawshank Redemption");
//            System.out.println("Movie :\n" + m2.getTitle() + "\n" + m2.getYear() + "\n"+ m2.getVisualisationDate() + "\n"+ m2.getPoints() + "\n");
//
//            MovieDto e = client.findMovieByTitle("test inexistant");
//            System.out.println("Movie :\n" + e.getTitle() + "\n" + e.getYear() + "\n"+ e.getVisualisationDate() + "\n"+ e.getPoints() + "\n");
//
//
//            transport.close();
//        } catch (TException e) {
//            System.err.println("Erreur RPC Thrift : " + e.getMessage());
//        }
//    }
}