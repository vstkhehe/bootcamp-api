select *
from tb_course c
         inner join tb_module m
                    on m.course_id = m.id
         inner join tb_lesson l
                    on l.module_id = l.id
         inner join tb_question q
                    on q.lesson_id = l.id
         inner join tb_answer a
                    on a.question_id = a.id