# language: pt
#
Funcionalidade: Regras do jogo

  Cenario: Iniciar partida
    Dado que o usuário está logado
    Quando inicia uma partida
    Então o sistema retorna um par único de filmes

  Cenario: Encerrrar partida
    Dado que o usuário está logado
    E participando de uma partida
    Quando encerra uma partida
    Então o sistema encerra a partida

  Cenario: Acerta qual filme possui maior pontuação
    Dado que o usuário está logado
    Quando tenta acertar qual filme possui maior pontuação
    E acerta
    Então o usuário ganha 1 ponto
    E o sistema retorna um par único de filmes

  Cenario: Erra qual filme possui maior pontuação
    Dado que o usuário está logado
    Quando tenta acertar qual filme possui maior pontuação
    E erra
    Então o usuário marca 1 erro
    E o sistema retorna um par único de filmes
    Quando erra 3 vezes
    Então perde o jogo