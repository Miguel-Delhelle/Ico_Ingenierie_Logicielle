package ico.AST.main;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PathToText {

	private Path filePath;
	
	public PathToText(String cheminSource) throws URISyntaxException{
		URI cheminInUri = new URI(cheminSource);
		Path lePath = Paths.get(cheminInUri);
		this.filePath = lePath;
	}
	
	public String[] getAllJavaContent() throws IOException {
		
		String[] allMyFilesInTxt;
		
		  try (Stream<Path> stream = Files.walk(this.filePath)) {
	            allMyFilesInTxt = stream
	                    .filter(Files::isRegularFile)
	                    .filter(path -> path.toString().endsWith(".java"))
	                    .map(path -> {
	                        try {
	                            return new String(Files.readAllBytes(path));
	                        } catch (IOException e) {
	                            throw new RuntimeException("Impossible de lire le fichier: " + path, e);
	                        }
	                    })
	                    .toArray(String[]::new);
	        }
		
		return allMyFilesInTxt;
		
	}
	
}
