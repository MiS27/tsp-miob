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
            simMatrix = numpy.zeros((runs+1, runs+1), dtype=float)
            runsMatrixes = []
            runsStart = getRunsStart(algs, instances, runs, alg, instance)
            sequencesLines = lines[runsStart : runsStart + runs + 1] 
            #print (sequencesLines)
            for line in sequencesLines:
                runsMatrixes.append(parseLine(line))
            for idxA, A in enumerate(runsMatrixes):
                for idxB, B in enumerate(runsMatrixes):
                    if idxA != idxB:
                        simMatrix[idxA][idxB] = sim(A,B)
            numpy.savetxt(algsList[alg] + "_" + instancesList[instance] + ".csv", simMatrix, delimiter = " ")
        
    
parseFile("matrix.csv", 4, 2, 10)