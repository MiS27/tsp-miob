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
    
print (sim(parseLine("2 0 1"), parseLine("1 0 2")))