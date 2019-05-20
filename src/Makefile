src := ./Main.java ./algorithms/*.java ./elements/*.java
out := Main
BUILD_DIR := ./../build
SOL_DIR := ./../solutions

.SILENT: buildSolutionDir makeBuildDir
.PHONY: default run clean buildSolutionDir makeBuildDir

default: compile

# Compilation targets
compile: makeBuildDir
	javac -d $(BUILD_DIR)/ $(src)

makeBuildDir:
	-mkdir $(BUILD_DIR)

# Execution targets
run: buildSolutionDir
	java -cp $(BUILD_DIR) $(out)

buildSolutionDir:
	-mkdir $(SOL_DIR)

# Cleanup targets
clean:
	rm -rf $(BUILD_DIR)
	rm -rf $(SOL_DIR)