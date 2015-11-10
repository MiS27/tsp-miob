library(ggplot2)
source("thirdParty.r")

all <- read.csv("full-report.csv", head=TRUE, sep="|")
algs <- unique(all$alg)
instances <- unique(all$instName)

proc <- summarySE(all, measurevar="solTime", groupvars=c("alg","instDim"))

plot <- ggplot(proc, aes(x=instDim, y=solTime, colour=alg)) +
geom_errorbar(aes(ymin=solTime-se, ymax=solTime+se), width=.1) +
geom_line() +
geom_point() #+ scale_y_log10()



print(plot)

