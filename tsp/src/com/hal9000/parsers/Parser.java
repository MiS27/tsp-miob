package com.hal9000.parsers;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rt on 14.10.15.
 */
public interface Parser {

    TSPInstance parse(InputStream input) throws IOException;
}
