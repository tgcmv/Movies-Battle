# language: pt
#
Funcionalidade: Ranking

  Cenario: Listar ranking
    Quando é solicitado a lista de ranking
    Então é exibido os melhores jogadores e suas pontuações
    E a pontuação é calculada com a quantidade de quizzes respondidos multiplicado pela porcentagem de acerto