package com.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.company.JSQuery;
import com.company.Parameter;
import com.company.QueryData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    private JSQuery program;

    @BeforeEach
    public void setUp() throws Exception {
        program = new JSQuery();
    }

    @Test
    @DisplayName("Testing there is no output file")
    public void testFileWritter() {

        String emptyFile = "";
        List<QueryData> list = new ArrayList<>();
        List<Parameter> params = new ArrayList<>();
        params.add(new Parameter("Site1", "name"));
        params.add(new Parameter("6410.88", "Power"));

        QueryData first = new QueryData("Site 1",-1671296,8,0,"Communications",640,"87111c51-08df-4b29-85c5-43803a994bdd", params);
        list.add(first);
        

    }

}
