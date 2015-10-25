from dbms.table import Table

Table.create('Student', ('id', 'name', 'email', 'address'))
Table.create('Employee', ('id', 'name', 'designation', 'Address'))

Table.index('Student', 'name')
Table.index('Employee', 'name')

students = Table('Student')
students.insert((1, 'Samuel L. Jackson', 'sjl@gmail.com', 'Somewhere in USA'))
print(students.search((None, 'Samuel L. Jackson', None, None)))

employees = Table('Employee')
employees.insert((1, 'Garrett', '123456789', 'New York'))
employees.insert((2, 'John Doe', 'N', 'London'))
employees.insert((3, 'Joe', 'P', 'Kazan'))
# employees.delete((2, None, None, None))
employees.insert((4, 3, 2, 1))
employees.update((4, None, None, None), (4, 3, 'hello', 'world'))
employees.update((4, None, None, None), (4, 'Joe', 'erwin', 'schrodinger'))
Table.index('Employee', 'name')
employees.read_index()
print(employees.search((None, 'Garrett', None, None)))
print(employees.search((None, 'Joe', None, None)))
print(employees.search((None, None, None, None)))

Table.index('Student', 'name')
Table.index('Employee', 'name')
