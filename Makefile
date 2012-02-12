lwjgl_jar := $(PWD)/libs/jar/lwjgl.jar
gson_jar := $(PWD)/libs/jar/gson-2.1.jar
slick_jar := $(PWD)/libs/jar/slick.jar

classpath := $(lwjgl_jar):$(gson_jar):$(slick_jar)

archive_dir := build
build_dir := bin
source_dir := src

xcf2png:
	echo todo

data-zip:
	$(RM) $(archive_dir)/data.zip
	cd $(archive_dir)/data; zip -Dr ../data.zip .

archive: data-zip jar


jar: build
	unzip -o $(lwjgl_jar) -d $(build_dir)
	unzip -o $(gson_jar) -d $(build_dir)
	unzip -o $(slick_jar) -d $(build_dir)
	$(RM) -r $(build_dir)/version $(build_dir)/META-INF
	cd $(build_dir); jar cfe ptg.jar main.Launcher *
	mv $(build_dir)/ptg.jar $(archive_dir)/

build:
	$(RM) -r $(build_dir)/*
	javac \
		-Xlint:all -g:none \
		-d $(build_dir) \
		-classpath $(classpath) \
		-sourcepath $(source_dir) \
		$(source_dir)/main/Launcher.java

.PHONY: build
