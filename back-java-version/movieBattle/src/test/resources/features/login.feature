# language: pt
#
Funcionalidade: Login do usuário

  Cenario: o Usuário deve fazer se cadastrar
    Dado que o usuario não tenha feito um sign up
    Quando ele informa os dados necessários e cria uma conta nova
    Então o sistema retorna status 201
    E autoriza o usuário e entrar/participar de uma nova partida

  Cenario: o Usuário deve fazer login
    Dado que o usuario já tenha feito um sign up
    Quando ele solicita um sign in
    Então o sistema retorna status 201
    E autoriza o usuário e entrar/participar de uma nova partida
