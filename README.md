<h1>API de gerenciamento de tarefas</h1>

<p>Esta é uma API REST de gerenciamento de tarefas desenvolvida em Spring Boot com Java 17, utilizando banco de dados PostgreSQL e Hibernate.</p>

<h2>Criar pessoa</h2>

<ul>
  <li>Método HTTP: POST</li>
  <li>Endpoint: <code>127.0.0.1:8098/v1/person</code></li>
  <li>Request:
    <pre><code>{
  "name": "Elisa",
  "department": "Financeiro"
}</code></pre>
  </li>
  <li>Response:
    <pre><code>{
  "message": "Cadastro realizado com sucesso."
}</code></pre>
  </li>
</ul>

<h2>Criar tarefa</h2>

<ul>
  <li>Método HTTP: POST</li>
  <li>Endpoint: <code>127.0.0.1:8098/v1/task</code></li>
  <li>Request:
    <pre><code>{
  "title": "Implementar funcionalidade Yyz",
  "description": "Desenvolver a funcionalidade Yyz.",
  "deadline": "2023-05-10T23:00:00",
  "department": "Financeiro"
}</code></pre>
  </li>
  <li>Response:
    <pre><code>{
  "message": "Cadastro realizado com sucesso."
}</code></pre>
  </li>
</ul>

<h2>Adicionar pessoa a tarefa</h2>

<ul>
  <li>Método HTTP: PUT</li>
  <li>Endpoint: <code>127.0.0.1:8098/v1/task/allocate/taskId/2/personId/3</code></li>
  <li>Response:
    <pre><code>{
  "message": "Eduardo adicionado a task: Implementar funcionalidade Y"
}</code></pre>
  </li>
</ul>

<h2>Finalizar tarefa</h2>

<ul>
  <li>Método HTTP: PUT</li>
  <li>Endpoint: <code>127.0.0.1:8098/v1/task/finalize/2</code></li>
  <li>Response:
    <pre><code>{
  "message": "Tarefa finalizada com sucesso."
}</code></pre>
  </li>
</ul>

<h2>Buscar as 3 últimas tarefas pendentes mais antigas</h2>

<ul>
  <li>Método HTTP: GET</li>
  <li>Endpoint: <code>127.0.0.1:8098/v1/task/pending</code></li>
  <li>Response:
    <pre><code>[
  {
    "id": 3,
    "title": "Implementar funcionalidade Yyz",
    "description": "Desenvolver a funcionalidade Yyz.",
    "deadline": "2023-01-10T23:00:00",
    "department": "Comercial",
    "completed": false
  },
  {
    "id": 6,
    "title": "Implementar funcionalidade Yyz",
    "description": "Desenvolver a funcionalidade Yyz.",
    "deadline": "2023-02-09T23:00:00",
    "department": "Comercial",
    "completed": false
  },
  {
    "id": 7,
    "title": "Revisar conteúdo do website",
    "description": "Verificar se o conteúdo do website está atualizado e corrigir erros ortográficos.",
    "deadline": "2023-02-15T23:00:00",
    "department": "Marketing",
    "completed": false
  }
]

Claro, aqui está a versão com formatação HTML:

<h2>GET: buscar todas as pessoas</h2>
<p>endpoint: 127.0.0.1:8098/v1/person</p>
<pre>
response: [
	{
		"name": "Eduardo",
		"department": "TI",
		"totalHoursOnTasks": "00:00"
	},
	{
		"name": "Bel",
		"department": "Comercial",
		"totalHoursOnTasks": "02:39"
	}
]
</pre>
<h2>GET: buscar pessoa por nome e periodo</h2>
<p>endpoint: 127.0.0.1:8098/v1/person/average-hours</p>
<h3>Request:</h3>
<ul>
	<li>QueryParametro: name (Eduardo)</li>
	<li>QueryParametro: startDate (2023-05-10)</li>
	<li>QueryParametro: endDate (2023-05-11)</li>
</ul>
<h3>Response:</h3>
<pre>
{
	"name": "Bel",
	"department": "Comercial",
	"averageHours": "01:19"
}
</pre>
<h2>Delete: deletar pessoa por id</h2>
<p>127.0.0.1:8098/v1/person/1</p>
<pre>
response: {
	"message": "Cadastro deletado com sucesso."
}
</pre>
<h2>Update: atualizar pessoa</h2>
<p>127.0.0.1:8098/v1/person/2</p>
<h3>request:</h3>
<pre>
{
	"name": "Eduardo",
	"department": "TI"
}
</pre>
<h3>response:</h3>
<pre>
{
	"message": "Cadastro atualizado com sucesso."
}
</pre>
