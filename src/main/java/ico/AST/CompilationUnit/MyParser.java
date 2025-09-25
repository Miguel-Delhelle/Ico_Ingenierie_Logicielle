package ico.AST.CompilationUnit;

import java.net.URISyntaxException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import ico.AST.main.PathToText;
import ico.AST.main.RepositorySelector;

public class MyParser {
	
	private ASTParser parser = ASTParser.newParser(AST.JLS21);
    private final CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
	

	MyParser() throws URISyntaxException {
		
		this.parser.setSource(new PathToText(new RepositorySelector().getLePath()).getAllMyFileInTxt());
		//parser.setSource(sourceCode.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
	}

}
