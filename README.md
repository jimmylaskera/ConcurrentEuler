## Trabalho Prático: Java Concurrency Utilities

O objetivo deste trabalho é estimular o projeto e implementação de soluções para problemas por meio
de programação concorrente, mais especificamente colocando em prática alguns mecanismos avançados
providos pela linguagem de programação Java dentre as Java Concurrent Utilities.

O número e, também conhecido como número de Euler (em homenagem ao matemático suíço Leonhard
Euler, 1707–1783), é uma constante matemática cujo valor número aproximado, truncado em 50 casas
decimais, é 2,71828182845904523536028747135266249775724709369995.

O número e possui diversas aplicações na Matemática, Estatística e Economia. Quando utilizado como
a base para um logaritmo, o logaritmo correspondente é chamado de logaritmo natural, escrito como
ln(x), de modo que ln(e) = 1.

A tarefa central a ser realizada neste trabalho consiste em projetar e implementar uma solução
concorrente para o cálculo do número e mediante uma série infinita utilizando threads independentes e
concorrentes. O desenvolvimento da solução deve de antemão visar pela busca de desenvolvimento de
software de qualidade, isto é, funcionando correta e eficientemente, tendo sido exaustivamente testada,
bem documentada e com tratamento adequado de eventuais exceções. Mais ainda, a implementação
deverá garantir corretude do programa com relação a concorrência.

Para gerenciamento das threads que executarão as tarefas definidas para o programa, devem ser
utilizados thread pools de diferentes tipos disponibilizados pelo framework Executor e sua classe
Executors; mais precisamente, fixed, cached e work stealing thread pools. Assim sendo, haverá três
versões diferentes do mesmo programa.
