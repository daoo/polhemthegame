#!/usr/bin/env ruby

header =
  [ "/*",
    " * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.",
    " */",
    "" ]

files = `find . -iname '*.java'`

files.split("\n").each do |file|
  lines = []
  File.open(file) do |f|
    lines = f.readlines
  end

  no_header = lines.drop_while { |l| !l.start_with?("package") }
  output = header + no_header

  File.open(file, 'w') do |f|
    f.puts output
  end
end
