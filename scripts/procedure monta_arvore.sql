DROP PROCEDURE IF EXISTS monta_arvore_resposta;

DELIMITER $$
CREATE PROCEDURE monta_arvore_resposta (pergunta_id int)
BEGIN
	declare i int default 0;
    declare id_resposta_root int default 0;
    declare cursor_resposta cursor for select r.id from resposta r where id_resposta is null and id_pergunta = pergunta_id;
    create temporary table arvore_montada(
		id int
        ,conteudo text
        ,id_pergunta int
        ,id_usuario int
        ,id_resposta int
        ,camada int
	);
	set @respostas_root = (select count(*) from resposta where id_resposta is null and id_pergunta = pergunta_id);
    
    open cursor_resposta;
    
    while i < @respostas_root
    do
        fetch cursor_resposta into id_resposta_root;
        
		insert into arvore_montada with recursive respostasFilho as (
			select *, 1 as n
			from resposta 
			where id = id_resposta_root and id_pergunta = pergunta_id
            
			UNION
            
			select r.*, rf.n + 1
			from respostasFilho rf 
			join resposta r on r.id_resposta = rf.id
		)
		select * from respostasFilho;
        
        set i = i + 1;
    end while;
    
    select * from arvore_montada;
    close cursor_resposta;
    drop table arvore_montada;
    
END $$
DELIMITER ;