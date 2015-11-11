library(ggplot2)
source("thirdParty.r")

all <- read.csv("full-report.csv", head=TRUE, sep="|")
algs <- unique(all$alg)
instances <- unique(all$instName)


proc <- summarySE(all, measurevar="solTime", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=solTime, colour=alg)) +
geom_errorbar(aes(ymin=solTime-se, ymax=solTime+se), width=.1) +
geom_line() +
geom_point() + scale_y_log10()

ggsave(file="timeCity2Opt.pdf", plot=plot)

proc <- summarySE(all, measurevar="optValDiff", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=optValDiff, colour=alg)) +
geom_errorbar(aes(ymin=optValDiff-se, ymax=optValDiff+se), width=.1) +
geom_line() +
geom_point() 


ggsave(file="optValDiffCity2Opt.ps", plot=plot)


proc <- summarySE(all, measurevar="optValDiffR", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=optValDiffR, colour=alg)) +
geom_errorbar(aes(ymin=optValDiffR-se, ymax=optValDiffR+se), width=.1) +
geom_line() +
geom_point() 


ggsave(file="optValDiffRCity2Opt.ps", plot=plot)


