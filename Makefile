### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

initial.class : initial.java SudokuGUI.class
	${JC} ${JCFLAGS} initial.java

resolveurGrille.class : resolveurGrille.java
	${JC} ${JCFLAGS} resolveurGrille.java

SudokuGUI.class : SudokuGUI.java resolveurGrille.class saveGrid.class
	${JC} ${JCFLAGS} SudokuGUI.java

saveGrid.class : saveGrid.java
	${JC} ${JCFLAGS} saveGrid.java

### REGLES OPTIONNELLES ###

run : initial.class
	${JVM} ${JVMFLAGS} initial
clean :
	-rm -f *.class

mrproper : clean Main.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###