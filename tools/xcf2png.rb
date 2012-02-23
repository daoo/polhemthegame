#!/usr/bin/env ruby

require 'find'
require 'fileutils'

SCRIPT="(define (do-convert infile outfile)
  (let* (
      (image (car (gimp-file-load RUN-NONINTERACTIVE infile infile)))
      (layer (car (gimp-image-merge-visible-layers image CLIP-TO-IMAGE))))
    (gimp-layer-resize-to-image-size layer)
    (gimp-file-save RUN-NONINTERACTIVE image layer outfile outfile)
    (gimp-image-delete image)
  )
)
(gimp-message-set-handler 1)"

indir  = File.expand_path(ARGV[0])
outdir = File.expand_path(ARGV[1])

gimp_cmds = []
Find.find(indir) do |path|
  if File.extname(path) == ".xcf"
    full_infile      = File.expand_path(path)
    relative_infile  = (full_infile.split(File::SEPARATOR) - indir.split(File::SEPARATOR)).join(File::SEPARATOR)
    relative_outfile = relative_infile.sub(".xcf", ".png")
    full_outfile     = File.join(outdir, relative_outfile)

    FileUtils.mkdir_p(File.dirname(full_outfile))

    gimp_cmds << "(gimp-message \"#{full_infile} to #{full_outfile}\")"
    gimp_cmds << "(do-convert \"#{full_infile}\" \"#{full_outfile}\")"
  end
end

gimp_cmds << "(gimp-quit 0)"

IO.popen("gimp -fdib -", "w+") do |io|
  io.puts SCRIPT
  io.puts gimp_cmds
  io.close_write
  while (line = io.gets) do
    puts line
  end
end
