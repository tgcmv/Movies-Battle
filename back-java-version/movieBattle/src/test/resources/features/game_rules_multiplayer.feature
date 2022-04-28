## language: pt
##
#Funcionalidade: Regras do jogo
#
#  Cenario: Criar uma partida
#    Dado que o usuário está logado
#    Quando cria uma partida de 'movie battle'
#    Então o sistema retorna status 201
#    E o código da partida para que outros jogadores possam participar
#
#  Cenario: Entra em uma partida
#    Dado que o usuário está logado
#    Quando informa o código de uma partida existente
#    Então o sistema retorna status 201
#    E o jogador aguarda o inicio da partida