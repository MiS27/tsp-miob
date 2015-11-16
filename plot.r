library(ggplot2)
source("thirdParty.r")

all <- read.csv("full-report.csv", head=TRUE, sep="|")
gs <- read.csv("full-report-gs.csv", head=TRUE, sep="|")

algs <- unique(all$alg)
instances <- unique(all$instName)


#done
proc <- summarySE(all, measurevar="optValDiffR", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=optValDiffR, colour=alg)) +
geom_errorbar(aes(ymin=optValDiffR-sd, ymax=optValDiffR+sd), width=.1) +
labs(x="Instance size", y="dist-R", colour="Alghoritm")+
geom_line() + geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1))
ggsave(file="2aRatioCITY.pdf", plot=plot)

proc <- summarySE(all, measurevar="optValDiff", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=optValDiff, colour=alg)) +
geom_errorbar(aes(ymin=optValDiff-sd, ymax=optValDiff+sd), width=.1) +
labs(x="Instance size", y="dist-R", colour="Alghoritm")+
geom_line() + geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="2aDiffCITY.pdf", plot=plot)


proc <- summarySE(all, measurevar="solTime", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=solTime, colour=alg)) +
geom_errorbar(aes(ymin=solTime-sd, ymax=solTime+sd), width=.1) +
labs(x="Instance size", y="time[s]", colour="Alghoritm")+
geom_line() +
geom_point() + scale_y_log10()+ theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="2bCitylog.pdf", plot=plot)


proc <- summarySE(all, measurevar="solTime", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=solTime, colour=alg)) +
geom_errorbar(aes(ymin=solTime-sd, ymax=solTime+sd), width=.1) +
labs(x="Instance size", y="time[s]", colour="Alghoritm")+
geom_line() +
geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="2bCity.pdf", plot=plot)



proc <- summarySE(all, measurevar="quality", groupvars=c("alg","solTime"))
plot <- ggplot(proc, aes(x=solTime, y=quality, colour=alg)) +
geom_errorbar(aes(ymin=quality-sd, ymax=quality+sd), width=.1) +
labs(x="Time[s]", y="Quality", colour="Alghoritm")+
geom_line() + scale_x_log10()+scale_y_log10()+
geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="2c.pdf", plot=plot)



proc <- summarySE(all, measurevar="solSteps", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=solSteps, colour=alg)) +
geom_errorbar(aes(ymin=solSteps-sd, ymax=solSteps+sd), width=.1) +
labs(x="Instance size", y="steps", colour="Alghoritm")+
geom_line() +
geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="2dsteps.pdf", plot=plot)



proc <- summarySE(all, measurevar="solChecked", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=solChecked, colour=alg)) +
geom_errorbar(aes(ymin=solChecked-sd, ymax=solChecked+sd), width=.1) +
labs(x="Instance size", y="solutions", colour="Alghoritm")+
geom_line() +
geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="2dsolutions.pdf", plot=plot)


proc <- summarySE(all, measurevar="solChecked", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=instDim, y=solChecked, colour=alg)) +
geom_errorbar(aes(ymin=solChecked-sd, ymax=solChecked+sd), width=.1) +
labs(x="Instance size", y="solutions", colour="Alghoritm")+
geom_line() + scale_y_log10()+
geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="2dsolutionslog.pdf", plot=plot)


plot <- ggplot(gs, aes(x=qStart, y=qStop, colour=alg)) +
labs(x="start", y="stop", colour="Alghoritm")+
geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="3.pdf", plot=plot)


proc <- summarySE(gs, measurevar="qStop", groupvars=c("alg","instDim"))
plot <- ggplot(proc, aes(x=N, y=qStop, colour=alg)) +
geom_errorbar(aes(ymin=qStop-sd, ymax=qStop+sd), width=.1) +
labs(x="start", y="stop", colour="Alghoritm")+
geom_point() + theme(legend.justification=c(0,1), legend.position=c(0,1)) 
ggsave(file="4.pdf", plot=plot)




