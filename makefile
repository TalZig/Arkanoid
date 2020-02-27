# 313326019
# zigdont1
compile: bin
	javac -d bin -cp biuoop-1.4.jar src/*/*.java
run:
	java -cp biuoop-1.4.jar:bin:resources gameclasses/Ass7Game 
run2:
	java -cp biuoop-1.4.jar:bin:resources gameclasses/Ass7Game our_level_sets_file_relative_to_resources_folder
jar:
	jar cfm ass7game.jar Manifest.mf -C bin . -C resources .
bin:
	mkdir bin
