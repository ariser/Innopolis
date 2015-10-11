CREATE OR REPLACE FUNCTION int_to_roman(num INT)
  RETURNS TEXT AS $$
DECLARE
  roman_digits       TEXT ARRAY;
  roman_digit_values INT ARRAY;
  digit_index        INT;
  i                  INT;
  result             TEXT;
BEGIN
  IF num < 1
  THEN
    RAISE EXCEPTION 'Invalid input';
  END IF;

  roman_digits := ARRAY ['I', 'V', 'X', 'L', 'C', 'D', 'M', E'V\u0332', E'X\u0332', E'L\u0332', E'C\u0332', E'D\u0332', E'M\u0332'];
  result := '';
  roman_digit_values := ARRAY [1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000];
  WHILE num > 0 LOOP
    -- find the largest roman digit lte num
    FOR i IN REVERSE array_length(roman_digit_values, 1)..1 LOOP
      digit_index := i;
      EXIT WHEN num >= roman_digit_values [i];
    END LOOP;
    -- check whether we can use subtractive notation for V, L or D.
    -- Make sure that the subtracted digit isn't V, L or D (can happen for underscored V)
    IF num + roman_digit_values [digit_index - 1] >= roman_digit_values [digit_index + 1] AND
       roman_digit_values [digit_index - 1] * 2 < roman_digit_values [digit_index]
    THEN
      result := result || roman_digits [digit_index - 1] || roman_digits [digit_index + 1];
      num := num - roman_digit_values [digit_index + 1] + roman_digit_values [digit_index - 1];
    -- check whether we can use subtractive notation for X, C or M. Make sure that the current digit isn't V, L or D.
    ELSEIF num + roman_digit_values [digit_index] >= roman_digit_values [digit_index + 1] AND
           roman_digit_values [digit_index] * 2 < roman_digit_values [digit_index + 1]
      THEN
        result := result || roman_digits [digit_index] || roman_digits [digit_index + 1];
        num := num - roman_digit_values [digit_index + 1] + roman_digit_values [digit_index];
    ELSE
      result := result || roman_digits [digit_index];
      num := num - roman_digit_values [digit_index];
    END IF;
  END LOOP;
  RETURN result;
END;
$$ LANGUAGE plpgsql;