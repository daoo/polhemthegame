xcf2png:
	echo todo

data-archive:
	$(RM) build/data.zip
	cd build/data; zip -Dr ../data.zip .
