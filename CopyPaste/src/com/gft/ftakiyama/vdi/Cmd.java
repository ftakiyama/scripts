package com.gft.ftakiyama.vdi;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.gft.ftakiyama.vdi.CopyPaster.Builder;

public class Cmd {
    
    private final Options options = new Options();
    private final CommandLineParser parser = new DefaultParser();
    private final HelpFormatter formatter = new HelpFormatter();

    public Cmd() {
        this.options.addOption(new Option("help", "print this message"));
        this.options.addOption(Option.builder().argName("src")
                                               .hasArg()
                                               .desc("data source")
                                               .longOpt("datasource")
                                               .build());
        this.options.addOption(Option.builder().argName("time in seconds")
                                               .hasArg()
                                               .desc("time to focus on destination")
                                               .longOpt("delay")
                                               .type(int.class)
                                               .build());
        this.options.addOption(Option.builder().argName("time in milliseconds")
                                               .hasArg()
                                               .desc("delay between key strokes")
                                               .longOpt("interval")
                                               .type(int.class)
                                               .build()); 
    }
    
    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        try {
            CommandLine line = cmd.parser.parse(cmd.options, args);
            Builder builder = CopyPaster.builder();
            if (line.hasOption("help")) {
                cmd.formatter.printHelp("copypaster", cmd.options);
                System.exit(0);
            }
            if (line.hasOption("delay")) {
                builder.delay(Integer.valueOf(line.getOptionValue("delay")));
            }
            if (line.hasOption("interval")) {
                builder.interval(Integer.valueOf(line.getOptionValue("interval")));
            }
            
            CopyPaster copy = builder.build();
            
            if (line.hasOption("datasource")) {
                copy.paste(new File(line.getOptionValue("datasource")));
            } else {
                copy.paste();
            }
            System.out.println("Done");
            
        } catch (ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
        }

    }

}
