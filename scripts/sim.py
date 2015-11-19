import numpy
def parseLine(line):
    sequence = [int(i) for i in line.split()]
    matrix = numpy.zeros((len(sequence), len(sequence)), dtype=bool)
    last = None
    for element in sequence:
        if last is not None:
            matrix[last][element] = True
            matrix[element][last] = True
        last = element
    matrix[last][sequence[0]] = True
    matrix[sequence[0]][last] = True
    return matrix


def sim(A, B):
    multiplied = numpy.multiply(A,B)
    return numpy.sum(multiplied)/(2*len(multiplied))

def getRunsStart(algs, instances, runs, alg, instance):
    linesPerInstance = runs + 2
    linesPerAlg = linesPerInstance * instances + 1
    return 2 + alg * linesPerAlg + instance * linesPerInstance
    
def parseFile(filename, algs, instances, runs):
    lines = None
    with open(filename) as f:
        lines = f.read().splitlines()
    algsList = lines[::instances*(runs+2)+1]
    instancesList = lines[1:instances*(runs+2):runs+2]
    for alg in range(algs):
        for instance in range(instances):
            simMatrix = numpy.zeros((runs+1, runs+2), dtype=float)
            runsMatrixes = []
            runsStart = getRunsStart(algs, instances, runs, alg, instance)
            sequencesLines = lines[runsStart : runsStart + runs + 1] 
            #print (sequencesLines)
            for line in sequencesLines:
                runsMatrixes.append(parseLine(line))
            for idxA, A in enumerate(runsMatrixes):
                simMatrix[idxA][0] = idxA
                for idxB, B in enumerate(runsMatrixes):
                    simMatrix[idxA][idxB + 1] = sim(A,B)
            caption = algsList[alg].lower() + " i instancji " + instancesList[instance].lower() 
            label = "tab:sim" + algsList[alg].lower() + "-" + instancesList[instance].lower() 
            header = "\\begin{table}\n\\begin{center}\n\\begin{tabular}{SSSSSSSSSSSS}\n & 0 & 1 & 2 & 3 & 4 & 5 & 6 & 7 & 8 & 9 & 10"
            footer = "\\end{tabular}\n\\tabcaption{" + caption + "}\n\\label{" + label + "}\\end{center}\n\\end{table}"
            numpy.savetxt(algsList[alg] + "_" + instancesList[instance] + ".tex", simMatrix, fmt = '%.3f', header = header, footer = footer, comments= '', delimiter = " & ", newline=' \\\\\n')
        
    
parseFile("matrix.csv", 4, 2, 10)