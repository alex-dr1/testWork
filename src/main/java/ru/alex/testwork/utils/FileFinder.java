package ru.alex.testwork.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileFinder
		extends SimpleFileVisitor<Path> {

	private final PathMatcher matcher;
	private final Path startingDir;
	private int numMatches = 0;

	public FileFinder(String startingDir, String pattern) {
		this.startingDir = Paths.get(startingDir);
		matcher = FileSystems.getDefault()
				.getPathMatcher("glob:" + pattern);
	}

	void find(Path file) {
		Path name = file.getFileName();
		if (name != null && matcher.matches(name)) {
			numMatches++;
		}
	}

	public int done() {
		try {
			Files.walkFileTree(startingDir, this);
		} catch (IOException e) {
//			TODO Exception
			e.printStackTrace();
		}
		return numMatches;
	}

	// Invoke the pattern matching
	// method on each file.
	@Override
	public FileVisitResult visitFile(Path file,
									 BasicFileAttributes attrs) {
		if(file.getParent().equals(startingDir)){
			find(file);
		}
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file,
										   IOException exc) {
//			TODO Exception
		System.err.println(exc);
		return CONTINUE;
	}
}


