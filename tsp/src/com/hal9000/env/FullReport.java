package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.solver.Solution;

import java.io.*;

/** Creates full report of tests
 * @see Report
 */
public class FullReport extends Report{
    /** Saves report to file
     * @param file path to file
     * @param sep separator for csv file
     * @param separate create separate files for each solver
     */
    @Override
    public void dump(String file, String sep, boolean separate){
        Writer writer = null;
        try{
            if(!separate) {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

                StringBuilder header = new StringBuilder();
                header.append("alg").append(sep)
                        .append("instName").append(sep)
                        .append("instDim").append(sep)
                        .append("instOptVal").append(sep)
                        .append("solTime").append(sep)
                        .append("solVal").append(sep)
                        .append("solSteps").append(sep)
                        .append("solChecked").append(sep)
                        .append("optValDiff").append(sep)
                        .append("optValDiffR").append(sep)
                        .append("startVal").append(sep)
                        .append("quality").append(sep)
                        .append("qStart").append(sep)
                        .append("qStop")
                        .append("\n");


                writer.write(header.toString());
            }
            for(String s : solutions.keySet()){
                if(separate){
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(s+"-"+file), "utf-8"));

                StringBuilder header = new StringBuilder();
                header.append("alg").append(sep)
                        .append("instName").append(sep)
                        .append("instDim").append(sep)
                        .append("instOptVal").append(sep)
                        .append("solTime").append(sep)
                        .append("solVal").append(sep)
                        .append("solSteps").append(sep)
                        .append("solChecked").append(sep)
                    .append("optValDiff").append(sep)
                        .append("optValDiffR").append(sep)
                        .append("startVal").append(sep)
                        .append("quality").append(sep)
                        .append("qStart").append(sep)
                        .append("qStop")
                        .append("\n");


                writer.write(header.toString());
                }
                for(TSPInstance instance : solutions.get(s).keySet()){
                    for(Solution solution : solutions.get(s).get(instance)){
                        writer.write
                                (new StringBuilder()
                                        .append(s).append(sep)
                                        .append(instance.getName()).append(sep)
                                        .append(instance.getDim()).append(sep)
                                        .append(instance.getOptimalValue()).append(sep)
                                        .append(solution.getTime()).append(sep)
                                        .append(instance.getCost(solution.getSolution())).append(sep)
                                        .append(solution.getSteps()).append(sep)
                                        .append(solution.getChecked()).append(sep)
                                        .append(instance.getCost(solution.getSolution())-instance.getOptimalValue()).append(sep)
                                        .append(instance.getCost(solution.getSolution())/instance.getOptimalValue()-1.0).append(sep)
                                                .append(solution.getStartCost()).append(sep)
                                                .append(solution.getQuality()).append(sep)
                                                .append(instance.getCost(solution.getSolution())/instance.getOptimalValue()-1.0).append(sep)
                                                .append(solution.getStartCost()/instance.getOptimalValue()-1.0)


                                                .append("\n")


                                        .toString()
                                );

                    }
                }

            }

        writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
