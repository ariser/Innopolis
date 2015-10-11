CREATE OR REPLACE FUNCTION r_add(
  roman_a TEXT,
  roman_b TEXT
)
  RETURNS TEXT AS $$
BEGIN
  RETURN int_to_roman(roman_to_int(roman_a) + roman_to_int(roman_b));
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION r_sub(
  roman_a TEXT,
  roman_b TEXT
)
  RETURNS TEXT AS $$
BEGIN
  RETURN int_to_roman(roman_to_int(roman_a) - roman_to_int(roman_b));
END;
$$ LANGUAGE plpgsql;