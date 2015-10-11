CREATE OR REPLACE FUNCTION roman_to_int(roman TEXT)
  RETURNS INT AS $$
DECLARE
  result                INT;
  chr                   VARCHAR(2);
  roman_rev             TEXT;
  roman_digits          VARCHAR(2) ARRAY;
  roman_digit_values    INT ARRAY;
  char_index            INT;
  digit_index           INT;
  prev_digit_index      INT;
  digits_in_a_row       INT;
  subtraction_performed BOOLEAN;
  prohibit_subtraction  BOOLEAN;
BEGIN
  roman_digits := ARRAY ['I', 'V', 'X', 'L', 'C', 'D', 'M', E'V\u0305', E'V\u0332', E'X\u0305', E'X\u0332', E'L\u0305', E'L\u0332', E'C\u0305', E'C\u0332', E'D\u0305', E'D\u0332', E'M\u0305', E'M\u0332'];
  roman_digit_values := ARRAY [1, 5, 10, 50, 100, 500, 1000, 5000, 5000, 10000, 10000, 50000, 50000, 100000, 100000, 500000, 500000, 1000000, 1000000];

  prev_digit_index := -1;
  result := 0;

  roman_rev = reverse(upper(roman));

  char_index := 1;
  digits_in_a_row := 0;
  subtraction_performed := FALSE;
  prohibit_subtraction := FALSE;

  -- loop from the last char. It's easier to handle subtractive notation that way.
  WHILE char_index <= length(roman) LOOP
    -- read digits one by one
    IF substr(roman_rev, char_index, 1) ~ E'\u0305' or substr(roman_rev, char_index, 1) ~ E'\u0332'
    THEN
      -- if the char is underline or overline, take two characters
      chr := substr(roman_rev, char_index, 2);
      char_index := char_index + 2;
    ELSE
      chr := substr(roman_rev, char_index, 1);
      char_index := char_index + 1;
    END IF;

    digit_index := 1;
    -- find the char in the predefined set. Raise an exception if the given char isn't there.
    WHILE chr <> reverse(roman_digits [digit_index]) LOOP
      digit_index := digit_index + 1;
      IF digit_index > array_length(roman_digits, 1)
      THEN
        RAISE EXCEPTION 'Invalid character --> %', chr;
      END IF;
    END LOOP;

    -- init prev_digit_index in the first iteration
    IF prev_digit_index < 0
    THEN
      prev_digit_index := digit_index;
    END IF;

    IF digit_index > prev_digit_index
    THEN
      -- move to a bigger roman numeral. Re-init all counters and flags.
      result := result + roman_digit_values [digit_index];
      digits_in_a_row := 0;
      subtraction_performed := FALSE;
      prohibit_subtraction := digit_index - prev_digit_index <= 1;
      prev_digit_index := digit_index;
    ELSEIF digit_index = prev_digit_index
      THEN
        -- the same numeral. If numeral is repeated more than 3 times, raise an exception
        result := result + roman_digit_values [digit_index];
        digits_in_a_row := digits_in_a_row + 1;
        IF digits_in_a_row > 3
        THEN
          RAISE EXCEPTION 'Invalid input';
        END IF;
    ELSE
      -- handle subtractive notation
      IF subtraction_performed OR prohibit_subtraction
      THEN
        -- only one subtractive notation per digits group is allowed
        -- subtraction is prohibited, if it doesn't make sense (IVI, XLX, etc.)
        RAISE EXCEPTION 'Invalid input';
      END IF;
      IF chr NOT IN ('I', 'X', 'C') OR abs(digit_index - prev_digit_index) > 2
      THEN
        -- I can be used only with V or X
        -- X can be used only with L or C
        -- C can be used only with D or M
        RAISE EXCEPTION 'Invalid input';
      END IF;
      result := result - roman_digit_values [digit_index];
      subtraction_performed := TRUE;
    END IF;
  END LOOP;

  RETURN result;
END;
$$ LANGUAGE plpgsql;