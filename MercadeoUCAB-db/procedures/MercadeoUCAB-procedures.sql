DELIMITER //

CREATE PROCEDURE CLONE_STUDY(IN study_id INTEGER, IN cloned_study_id INTEGER)
BEGIN
    DECLARE finished INTEGER DEFAULT 0;
    DECLARE finished_inner_loop INTEGER DEFAULT 0;
    DECLARE question_id INTEGER DEFAULT 0;
    DECLARE option_id INTEGER DEFAULT 0;
    DECLARE cloned_question_id INTEGER DEFAULT 0;
    DECLARE cloned_option_id INTEGER DEFAULT 0;

    -- Declare variables from question table
    DECLARE qpregunta VARCHAR(90) DEFAULT "";
    DECLARE qstatus INTEGER DEFAULT 0;
    DECLARE qfk_tipo INTEGER DEFAULT 0;

    -- Declare variables from pregunta_cat_subcat
    DECLARE qcs_fk_categoria INTEGER DEFAULT 0;
    DECLARE qcs_fk_subcategoria INTEGER DEFAULT 0;

    -- Declare variables from option
    DECLARE ovalor VARCHAR(90) DEFAULT null;
    DECLARE orango_inicial INTEGER DEFAULT null;
    DECLARE orango_final INTEGER DEFAULT null;

    -- Cursor for questions in study
    DECLARE cursorQuestions 
        CURSOR FOR 
            SELECT fk_pregunta FROM empresag.pregunta_estudio WHERE fk_estudio = study_id;

    -- Declare NOT FOUND handler
    DECLARE CONTINUE HANDLER
    FOR NOT FOUND SET finished = 1;

    OPEN cursorQuestions;

    getQuestions: LOOP
        FETCH cursorQuestions INTO question_id;
        IF FINISHED = 1 THEN
            LEAVE getQuestions;

        END IF;

        -- Build question list
        SELECT pregunta, status, fk_tipo 
        INTO qpregunta, qstatus, qfk_tipo 
        FROM empresag.pregunta WHERE id = question_id;
        
        SELECT fk_categoria, fk_subcategoria 
        INTO qcs_fk_categoria, qcs_fk_subcategoria 
        FROM empresag.pregunta_cat_subcat WHERE fk_pregunta = question_id;

        INSERT INTO empresag.pregunta (pregunta, status, fk_tipo) VALUES (qpregunta, qstatus, qfk_tipo);
        SET cloned_question_id = LAST_INSERT_ID();

        INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria) VALUES (cloned_question_id, qcs_fk_categoria, qcs_fk_subcategoria);

        INSERT INTO empresag.pregunta_estudio (requerido, fk_estudio, fk_pregunta) VALUES (1, cloned_study_id, cloned_question_id);

        -- Open cursor for question list
        IF qfk_tipo <> 1 THEN            
            BEGIN
            DECLARE cursorOptions 
                CURSOR FOR 
                    SELECT fk_opcion FROM empresag.posible_respuesta WHERE fk_pregunta = question_id;

            DECLARE CONTINUE HANDLER 
            FOR NOT FOUND SET finished_inner_loop = 1;

            OPEN cursorOptions;

            getOptions: LOOP
                FETCH cursorOptions INTO option_id;
                IF finished_inner_loop = 1 THEN 
                LEAVE getOptions;

                END IF;

                -- Build option list
                IF qfk_tipo <> 5 THEN
                    SELECT valor 
                    INTO ovalor
                    FROM empresag.opcion WHERE id = option_id; 
                
                ELSE
                    SELECT rango_inicial, rango_final
                    INTO orango_inicial, orango_final
                    FROM empresag.opcion WHERE id = option_id; 
                END IF;

                
                INSERT INTO empresag.opcion (valor, rango_inicial, rango_final) VALUES (ovalor, orango_inicial, orango_final);
                SET cloned_option_id = LAST_INSERT_ID();

                INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion) VALUES (cloned_question_id, cloned_option_id);
                
            END LOOP getOptions;
            CLOSE cursorOptions;
            END;
        END IF;

    END LOOP getQuestions;
    CLOSE cursorQuestions;
END //
DELIMITER ;