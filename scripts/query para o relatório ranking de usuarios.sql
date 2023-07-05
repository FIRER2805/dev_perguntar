select u.nome
	,count(r.id) as qtd_respostas
    ,(select count(r2.id) from resposta r2
		where u.id = r2.id_usuario and r2.solucao = 1) as resposta_solucao
	,case when ativo = 1 then 'sim' else 'não' end as ativo
    , count(p.id) as qntd_perguntas
from usuario u
left join resposta r on r.id_usuario = u.id
left join pergunta p on p.id_usuario = u.id
group by u.nome
order by resposta_solucao;