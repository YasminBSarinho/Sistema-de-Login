document.addEventListener("DOMContentLoaded", async () => {
    try {
        const resposta = await fetch("http://localhost:8080/home/empreendimentos");
        const empreendimentos = await resposta.json();

        const tabela = document.getElementById("tabelaEmpreendimentos");

        empreendimentos.forEach(e => {
            const linha = document.createElement("tr");

            linha.innerHTML = `
        <td class="py-3 px-4">${e.nome}</td>
        <td class="py-3 px-4">${e.situacao}</td>
        <td class="py-3 px-4">${formatarData(e.dataAssinatura)}</td>
        <td class="py-3 px-4">${e.codigoOperacao}</td>
        <td class="py-3 px-4">R$ ${formatarMoeda(e.valorDesembolsado)}</td>
        <td class="py-3 px-4">${e.construtora?.nome ?? "N/A"}</td>
      `;

            tabela.appendChild(linha);
        });

    } catch (erro) {
        console.error("Erro ao buscar empreendimentos:", erro);
    }
});

function formatarData(dataISO) {
    if (!dataISO) return "—";
    const [ano, mes, dia] = dataISO.split("-");
    return `${dia}/${mes}/${ano}`;
}

function formatarMoeda(valor) {
    return Number(valor).toLocaleString("pt-BR", { style: "decimal", minimumFractionDigits: 2 });
}
