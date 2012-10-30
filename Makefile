archive_dir   := $(PWD)/build
build_dir     := $(PWD)/bin
data_dir      := $(archive_dir)/data
libs_dir      := $(PWD)/libs
resources_dir := $(PWD)/resources
source_dir    := $(PWD)/src

lwjgl_jar := $(libs_dir)/jar/lwjgl.jar
gson_jar  := $(libs_dir)/jar/gson-2.1.jar
slick_jar := $(libs_dir)/jar/slick.jar

classpath := $(lwjgl_jar):$(gson_jar):$(slick_jar)

json := $(resources_dir)/bosses.js \
	$(resources_dir)/campaigns \
	$(resources_dir)/config.js \
	$(resources_dir)/creeps.js \
	$(resources_dir)/levels/ \
	$(resources_dir)/players.js \
	$(resources_dir)/projectiles.js \
	$(resources_dir)/shop.js \
	$(resources_dir)/weapons.js

launchers := $(PWD)/tools/linux.sh \
             $(PWD)/tools/windows.bat

all: clean build jar data

release: clean build launch-scripts libs jar data data-zip
	cd $(archive_dir); zip -Dr ptg.zip .

clean: clean-archive clean-build

launch-scripts:
	cp $(launchers) $(archive_dir)/

libs:
	cp -r $(libs_dir)/bin/* $(archive_dir)/

clean-archive:
	rm -rf $(archive_dir)/*

clean-build:
	rm -rf $(build_dir)/*

data-zip:
	rm -f $(archive_dir)/data.zip
	cd $(data_dir); zip -Dr ../data.zip .
	rm -rf $(archive_dir)/data/

data:
	mkdir -p $(data_dir)/
	./tools/xcf2png.rb $(resources_dir)/ $(data_dir)/
	ln -fsn $(resources_dir)/config.js $(archive_dir)/
	ln -fsn $(json) $(data_dir)/
	mkdir -p $(data_dir)/textures/levels/ \
		$(data_dir)/textures/menu/ \
		$(data_dir)/textures/projectiles/ \
		$(data_dir)/textures/shop/ \
		$(data_dir)/textures/text/
	ln -fsn $(resources_dir)/textures/*.png $(data_dir)/textures/
	ln -fsn $(resources_dir)/textures/levels/*.png $(data_dir)/textures/levels/
	ln -fsn $(resources_dir)/textures/menu/*.png $(data_dir)/textures/menu/
	ln -fsn $(resources_dir)/textures/projectiles/*.png $(data_dir)/textures/projectiles/
	ln -fsn $(resources_dir)/textures/shop/*.png $(data_dir)/textures/shop/
	ln -fsn $(resources_dir)/textures/text/*.png $(data_dir)/textures/text/

jar:
	unzip -o $(lwjgl_jar) -d $(build_dir)
	unzip -o $(gson_jar) -d $(build_dir)
	unzip -o $(slick_jar) -d $(build_dir)
	$(RM) -r $(build_dir)/version $(build_dir)/META-INF
	cd $(build_dir); jar cfe ptg.jar main.Launcher *
	mv $(build_dir)/ptg.jar $(archive_dir)/

build:
	javac \
		-Xlint:all -g:none \
		-d $(build_dir) \
		-classpath $(classpath) \
		-sourcepath $(source_dir) \
		$(source_dir)/game/Launcher.java

.PHONY: build data clean libs launch-scripts
