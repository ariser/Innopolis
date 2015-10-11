CREATE OR REPLACE FUNCTION fizzbuzz()
  RETURNS VOID AS $$
DECLARE i INT;
BEGIN
  FOR i IN 1..100 LOOP
    IF i % 15 = 0
    THEN
      RAISE NOTICE 'FizzBuzz';
    ELSEIF i % 3 = 0
      THEN
        RAISE NOTICE 'Fizz';
    ELSEIF i % 5 = 0
      THEN
        RAISE NOTICE 'Buzz';
    ELSE
      RAISE NOTICE '%', i;
    END IF;
  END LOOP;
END;
$$ LANGUAGE plpgsql;