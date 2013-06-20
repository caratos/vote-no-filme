<%@ include file="../../../header.jsp" %>


<h2>Filmes</h2>

<form action="/filmes/adiciona" method="post" accept-charset="utf-8">
<fieldset>
     <legend>Dados obrigatórios *</legend>
     
     <label for="filme.nome">Nome:</label>
     <input id="filme.nome" type="text" name="filme.nome" size="80" value="${filme.nome}"/>
</fieldset>

<%@ include file="../../../footer.jsp" %>