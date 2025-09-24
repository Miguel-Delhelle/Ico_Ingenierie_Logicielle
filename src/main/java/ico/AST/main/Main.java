package ico.AST.main;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String[] allSourceCode = new PathToText("file:/home/e20240000337/git/hotel_rest_tp3").getAllJavaContent();

        ASTParser parser = ASTParser.newParser(AST.JLS21); // Nous spécifions la version de Java (ici, Java 21)
        
        for (String sourceCode : allSourceCode) {
	        parser.setSource(sourceCode.toCharArray());
	        parser.setKind(ASTParser.K_COMPILATION_UNIT);
	        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	        cu.accept(new ASTVisitor() {
	
	            @Override
	            public boolean visit(TypeDeclaration node) {
	                String className = node.getName().getIdentifier();
	                System.out.println("Classe trouvée : " + className);
	                return true;
	            }
	
	            @Override
	            public boolean visit(MethodDeclaration node) {
	                String methodName = node.getName().getIdentifier();
	                System.out.println("  Méthode trouvée : " + methodName);
	                System.out.println("    Type de retour : " + node.getReturnType2());
	                return false;
	            }
	        });
        }
    }
}