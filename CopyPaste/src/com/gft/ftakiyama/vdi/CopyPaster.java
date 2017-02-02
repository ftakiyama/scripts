package com.gft.ftakiyama.vdi;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CopyPaster {
    
    public static final class Builder {
        private int delay = 2000;
        private int interval = 1;
        public Builder delay(int value) {
            this.delay = 1000 * value;
            return this;
        }
        public Builder interval(int value) {
            this.interval = value;
            return this;
        }
        public CopyPaster build() {
            return new CopyPaster(this);
        }
    }
    
    private final Typer typer;
    private final int delay; 
    
    private CopyPaster(Builder builder) {
        this.typer = new Typer(builder.interval);
        this.delay = builder.delay;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public void paste() {
        try {
            Thread.sleep(this.delay);
            String data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
            typer.type(data);
        } catch (HeadlessException | UnsupportedFlavorException | IOException | InterruptedException e) {
            e.printStackTrace();
        } 
    }
    
    public void paste(File file) {
        try {
            Thread.sleep(this.delay);
            Files.lines(file.toPath())
                 .forEach(typer::type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            typer.releaseAll();
        }
    }
}
