import static com.br.ada.utilidade.SenhaUtil.checarSenhaNoPadrao;

public class LoginTeste {
    public static void main(String[] args) {
        System.out.println(checarSenhaNoPadrao("brunak"));
        System.out.println(checarSenhaNoPadrao("Brunak8899"));
        System.out.println(checarSenhaNoPadrao("Brunak@"));
        System.out.println(checarSenhaNoPadrao("Br@k89"));
        System.out.println(checarSenhaNoPadrao("Fortes12@"));
    }
}
