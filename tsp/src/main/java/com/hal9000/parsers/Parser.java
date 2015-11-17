package com.hal9000.parsers;

import com.hal9000.data.TSPInstance;

import java.io.IOException;
import java.io.InputStream;


/** Parser interface */
public interface Parser {
    TSPInstance parse(InputStream input) throws IOException;
    TSPInstance parse(InputStream input, InputStream test) throws IOException;
}
