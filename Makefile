xcf2png:
	echo todo

data-archive:
	$(RM) data.zip
	cd data; zip -Dr ../data.zip .
