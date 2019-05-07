INSERT INTO public.animal(animal_name)
    VALUES ('Simon'), ('Cumali'), ('Henkie'), ('Jeroen'), ('Rico');

	

insert into ENCLOSURE values 
(1), (2), (3), (4), (5);

insert into ANIMAL_ENCLOSURE values
(1, 3, current_date - 7, null),
(2, 5, current_date, null),
(3, 2, current_date - 60, null),
(4, 1, current_date - 100, current_date),
(5, 4, current_date, null);