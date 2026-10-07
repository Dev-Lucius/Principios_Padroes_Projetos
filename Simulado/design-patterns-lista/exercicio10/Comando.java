public interface Comando {
    void executar();

    void desfazer(); // desafio: cada comando sabe se reverter
}
