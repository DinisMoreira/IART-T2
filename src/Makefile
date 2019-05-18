src := ./Main.java ./algorithms/*.java ./elements/*.java
out := Main
BUILD_DIR := ./../build

.PHONY: all run clean

all:
	-mkdir $(BUILD_DIR)
	javac -d $(BUILD_DIR)/ $(src)

run:
ifeq ($(file), )
	$(error "missing var file definition")
endif
	java -cp $(BUILD_DIR) $(out) $(file)

clean:
	rm -rf $(BUILD_DIR)