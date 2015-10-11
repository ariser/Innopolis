--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE categories (
    id integer NOT NULL,
    name character varying
);


--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- Name: cities; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE cities (
    id integer NOT NULL,
    name character varying,
    votes_balance integer
);


--
-- Name: cities_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE cities_id_seq OWNED BY cities.id;


--
-- Name: games; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE games (
    id integer NOT NULL,
    opponent character varying,
    points_scored integer,
    points_suffered integer
);


--
-- Name: games_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE games_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: games_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE games_id_seq OWNED BY games.id;


--
-- Name: nominations; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE nominations (
    id integer NOT NULL,
    year integer,
    category_id integer,
    person_id integer,
    movie character varying,
    "character" character varying,
    won boolean
);


--
-- Name: nominations_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE nominations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: nominations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE nominations_id_seq OWNED BY nominations.id;


--
-- Name: people; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE people (
    id integer NOT NULL,
    first_name character varying,
    last_name character varying
);


--
-- Name: people_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE people_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: people_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE people_id_seq OWNED BY people.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY cities ALTER COLUMN id SET DEFAULT nextval('cities_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY games ALTER COLUMN id SET DEFAULT nextval('games_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY nominations ALTER COLUMN id SET DEFAULT nextval('nominations_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY people ALTER COLUMN id SET DEFAULT nextval('people_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: -
--

COPY categories (id, name) FROM stdin;
1	Leading Actor
2	Supporting Actor
3	Leading Actress
4	Supporting Actress
\.


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('categories_id_seq', 4, true);


--
-- Data for Name: cities; Type: TABLE DATA; Schema: public; Owner: -
--

COPY cities (id, name, votes_balance) FROM stdin;
1	Charlotte	2784
2	Raleigh	-5473
3	Greensboro	-8417
4	Durham	8069
5	Winston-Salem	9208
6	Fayetteville	1518
7	Cary	-1155
8	Wilmington	1257
9	High Point	3047
10	Greenville	-6453
11	Asheville	6532
12	Concord	-4040
13	Gastonia	-4774
14	Jacksonville	3655
15	Chapel Hill	-1843
16	Rocky Mount	1856
17	Burlington	1600
18	Huntersville	1105
19	Wilson	5723
20	Kannapolis	-1432
21	Apex	-1757
22	Hickory	-4057
23	Goldsboro	-6432
24	Indian Trail	-1587
25	Mooresville	-2029
26	Wake Forest	1768
27	Monroe	-143
28	Salisbury	-3437
29	New Bern	4758
30	Sanford	-8538
31	Matthews	-2907
32	Holly Springs	-609
33	Thomasville	-3080
34	Cornelius	-1991
35	Garner	-585
36	Asheboro	-5605
37	Statesville	-5928
38	Mint Hill	5208
39	Kernersville	2951
40	Morrisville	-6171
41	Lumberton	-1778
42	Kinston	2979
43	Fuquay-Varina	7487
44	Havelock	-2917
45	Carrboro	2758
46	Shelby	8045
47	Clemmons	3651
48	Lexington	-2113
49	Elizabeth City	-2511
50	Boone	2607
\.


--
-- Name: cities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cities_id_seq', 1, false);


--
-- Data for Name: games; Type: TABLE DATA; Schema: public; Owner: -
--

COPY games (id, opponent, points_scored, points_suffered) FROM stdin;
1	Cleveland Browns	30	27
2	Baltimore Ravens	6	26
3	Carolina Panthers	37	19
4	Tampa Bay Buccaneers	24	27
5	Jacksonville Jaguars	17	9
6	Cleveland Browns	10	31
7	Houston Texans	30	23
8	Indianapolis Colts	51	34
9	Baltimore Ravens	43	23
10	New York Jets	13	20
11	Tennessee Titans	27	24
12	New Orleans Saints	32	35
13	Cincinnati Bengals	42	21
14	Atlanta Falcons	27	20
15	Kansas City Chiefs	20	12
16	Cincinnati Bengals	27	17
17	Baltimore Ravens	17	30
\.


--
-- Name: games_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('games_id_seq', 1, false);


--
-- Data for Name: nominations; Type: TABLE DATA; Schema: public; Owner: -
--

COPY nominations (id, year, category_id, person_id, movie, "character", won) FROM stdin;
1	2010	1	1	Biutiful	Uxbal	f
2	2010	1	2	True Grit	Rooster Cogburn	f
3	2010	1	3	The Social Network	Mark Zuckerberg	f
4	2010	1	4	The King's Speech	King George VI	t
5	2010	1	5	127 Hours	Aron Ralston	f
6	2009	1	2	Crazy Heart	Bad Blake	t
7	2009	1	6	Up in the Air	Ryan Bingham	f
8	2009	1	4	A Single Man	George	f
9	2009	1	7	Invictus	Nelson Mandela	f
10	2009	1	8	The Hurt Locker	Staff Sergeant William James	f
11	2008	1	9	The Visitor	Walter	f
12	2008	1	10	Frost/Nixon	Richard Nixon	f
13	2008	1	11	Milk	Harvey Milk	t
14	2008	1	12	The Curious Case of Benjamin Button	Benjamin Button	f
15	2008	1	13	The Wrestler	Randy	f
16	2007	1	6	Michael Clayton	Michael Clayton	f
17	2007	1	14	There Will Be Blood	Daniel Plainview	t
18	2007	1	15	Sweeney Todd The Demon Barber of Fleet Street	Sweeney Todd	f
19	2007	1	16	In the Valley of Elah	Hank Deerfield	f
20	2007	1	17	Eastern Promises	Nikolai	f
21	2006	1	18	Blood Diamond	Danny Archer	f
22	2006	1	19	Half Nelson	Dan Dunne	f
23	2006	1	20	Venus	Maurice	f
24	2006	1	21	The Pursuit of Happyness	Chris Gardner	f
25	2006	1	22	The Last King of Scotland	Idi Amin	t
26	2005	1	23	Capote	Truman Capote	t
27	2005	1	24	Hustle & Flow	DJay	f
28	2005	1	25	Brokeback Mountain	Ennis Del Mar	f
29	2005	1	26	Walk the Line	John R. Cash	f
30	2005	1	27	Good Night, and Good Luck.	Edward R. Murrow	f
31	2004	1	28	Hotel Rwanda	Paul Rusesabagina	f
32	2004	1	15	Finding Neverland	Sir James Matthew Barrie	f
33	2004	1	18	The Aviator	Howard Hughes	f
34	2004	1	29	Million Dollar Baby	Frankie Dunn	f
35	2004	1	30	Ray	Ray Charles	t
36	2003	1	15	Pirates of the Caribbean: The Curse of the Black Pearl	Jack Sparrow	f
37	2003	1	31	House of Sand and Fog	Behrani	f
38	2003	1	32	Cold Mountain	Inman	f
39	2003	1	33	Lost in Translation	Bob Harris	f
40	2003	1	11	Mystic River	Jimmy Markum	t
41	2002	1	34	The Pianist	Wladyslaw Szpilman	t
42	2002	1	35	Adaptation	Charlie Kaufman & Donald Kaufman	f
43	2002	1	36	The Quiet American	Thomas Fowler	f
44	2002	1	14	Gangs of New York	Bill 'The Butcher' Cutting	f
45	2002	1	37	About Schmidt	Warren Schmidt	f
46	2001	1	38	A Beautiful Mind	John Nash	f
47	2001	1	11	I Am Sam	Sam Dawson	f
48	2001	1	21	Ali	Muhammad Ali	f
49	2001	1	39	Training Day	Alonzo	t
50	2001	1	40	In the Bedroom	Matt Fowler	f
51	2000	1	1	Before Night Falls	Reinaldo Arenas	f
52	2000	1	38	Gladiator	Maximus Decimus Meridius	t
53	2000	1	41	Cast Away	Chuck Noland	f
54	2000	1	42	Pollock	Jackson Pollock	f
55	2000	1	43	Quills	The Marquis de Sade	f
56	1999	1	38	The Insider	Jeffrey Wigand	f
57	1999	1	44	The Straight Story	Alvin Straight	f
58	1999	1	11	Sweet and Lowdown	Emmet Ray	f
59	1999	1	45	American Beauty	Lester Burnham	t
60	1999	1	39	The Hurricane	Rubin 'Hurricane' Carter	f
61	1998	1	46	Life Is Beautiful	Guido	t
62	1998	1	41	Saving Private Ryan	Captain Miller	f
63	1998	1	47	Gods and Monsters	James Whale	f
64	1998	1	48	Affliction	Wade Whitehouse	f
65	1998	1	49	American History X	Derek	f
66	1997	1	50	Good Will Hunting	Will Hunting	f
67	1997	1	51	The Apostle	The Apostle E.F.	f
68	1997	1	52	Ulee's Gold	Ulee Jackson	f
69	1997	1	53	Wag the Dog	Stanley Motss	f
70	1997	1	37	As Good as It Gets	Melvin Udall	t
71	1996	1	54	Jerry Maguire	Jerry Maguire	f
72	1996	1	55	The English Patient	Almasy	f
73	1996	1	56	The People vs. Larry Flynt	Larry Flynt	f
74	1996	1	43	Shine	David Helfgott	t
75	1996	1	57	Sling Blade	Karl Childers	f
76	1995	1	35	Leaving Las Vegas	Ben Sanderson	t
77	1995	1	58	Mr. Holland's Opus	Glenn Holland	f
78	1995	1	59	Nixon	Richard Nixon	f
79	1995	1	11	Dead Man Walking	Matthew Poncelet	f
80	1995	1	60	The Postman (Il Postino)	Mario	f
81	1994	1	7	The Shawshank Redemption	Red	f
82	1994	1	41	Forrest Gump	Forrest Gump	t
83	1994	1	61	The Madness of King George	King George III	f
84	1994	1	62	Nobody's Fool	Sully	f
85	1994	1	63	Pulp Fiction	Vincent Vega	f
86	1993	1	14	In the Name of the Father	Gerry Conlon	f
87	1993	1	64	What's Love Got to Do with It	Ike Turner	f
88	1993	1	41	Philadelphia	Andrew Beckett	t
89	1993	1	59	The Remains of the Day	Mr. Stevens	f
90	1993	1	65	Schindler's List	Oskar Schindler	f
91	1992	1	66	Chaplin	Charles Chaplin	f
92	1992	1	29	Unforgiven	Bill Munny	f
93	1992	1	67	Scent of a Woman	Lt. Col. Frank Slade	t
94	1992	1	68	The Crying Game	Fergus	f
95	1992	1	39	Malcolm X	Malcolm X	f
96	1991	1	69	Bugsy	Bugsy Siegel	f
97	1991	1	70	Cape Fear	Max Cady	f
98	1991	1	59	The Silence of the Lambs	Dr. Hannibal Lecter	t
99	1991	1	48	The Prince of Tides	Tom Wingo	f
100	1991	1	71	The Fisher King	Parry	f
101	1990	1	72	Dances With Wolves	Lieutenant Dunbar	f
102	1990	1	70	Awakenings	Leonard Lowe	f
103	1990	1	73	Cyrano de Bergerac	Cyrano de Bergerac	f
104	1990	1	74	The Field	Bull McCabe	f
105	1990	1	75	Reversal of Fortune	Claus Von Bulow	t
106	1989	1	76	Henry V	Henry V	f
107	1989	1	54	Born on the Fourth of July	Ron Kovic	f
108	1989	1	77	My Left Foot	Christy Brown	t
109	1989	1	7	Driving Miss Daisy	Hoke Colburn	f
110	1989	1	71	Dead Poets Society	John Keating	f
111	1988	1	78	Mississippi Burning	Anderson	f
112	1988	1	41	Big	Josh	f
113	1988	1	53	Rain Man	Raymond Babbitt	t
114	1988	1	79	Stand and Deliver	Jaime Escalante	f
115	1988	1	80	Pelle the Conqueror	Lasse Karlsson	f
116	1987	1	81	Wall Street	Gordon Gekko	t
117	1987	1	82	Broadcast News	Tom Grunick	f
118	1987	1	83	Dark Eyes	Romano	f
119	1987	1	37	Ironweed	Francis Phelan	f
120	1987	1	71	Good Morning, Vietnam	Adrian Cronauer	f
121	1986	1	84	Round Midnight	Dale Turner	f
122	1986	1	85	Mona Lisa	George	f
123	1986	1	82	Children of a Lesser God	James	f
124	1986	1	62	The Color of Money	Eddie Felson	t
125	1986	1	86	Salvador	Richard Boyle	f
126	1985	1	87	Witness	John Book	f
127	1985	1	88	Murphy's Romance	Murphy Jones	f
128	1985	1	82	Kiss of the Spider Woman	Luis Molina	t
129	1985	1	37	Prizzi's Honor	Charley Partanna	f
130	1985	1	89	Runaway Train	Manny	f
131	1984	1	90	Amadeus	Antonio Salieri	t
132	1984	1	2	Starman	Starman	f
133	1984	1	91	Under the Volcano	Geoffrey Firmin	f
134	1984	1	92	Amadeus	Wolfgang Amadeus Mozart	f
135	1984	1	93	The Killing Fields	Sydney Schanberg	f
136	1983	1	36	Educating Rita	Dr. Frank Bryant	f
137	1983	1	94	Reuben, Reuben	Gowan McGland	f
138	1983	1	95	The Dresser	Norman	f
139	1983	1	51	Tender Mercies	Mac Sledge	t
140	1983	1	91	The Dresser	Sir	f
141	1982	1	53	Tootsie	Michael Dorsey/Dorothy Michaels	f
142	1982	1	31	Gandhi	Mahatma Gandhi	t
143	1982	1	96	Missing	Edmund Horman	f
144	1982	1	62	The Verdict	Frank Galvin	f
145	1982	1	20	My Favorite Year	Alan Swann	f
146	1981	1	69	Reds	John 'Jack' Reed	f
147	1981	1	97	On Golden Pond	Norman Thayer, Jr.	t
148	1981	1	98	Atlantic City	Lou	f
149	1981	1	99	Arthur	Arthur Bach	f
150	1981	1	62	Absence of Malice	Michael Gallagher	f
151	1980	1	70	Raging Bull	Jake LaMotta	t
152	1980	1	51	The Great Santini	Bull Meechum	f
153	1980	1	100	The Elephant Man	John Merrick	f
154	1980	1	96	Tribute	Scottie Templeton	f
155	1980	1	20	The Stunt Man	Eli Cross	f
156	1979	1	53	Kramer vs. Kramer	Ted Kramer	t
157	1979	1	96	The China Syndrome	Jack Godell	f
158	1979	1	67	...And Justice for All	Arthur Kirkland	f
159	1979	1	101	All That Jazz	Joe Gideon	f
160	1979	1	102	Being There	John Chance, the gardener (aka Chauncey Gardiner)	f
161	1978	1	69	Heaven Can Wait	Joe Pendleton (alias, Leo Farnsworth, Tom Jarrett)	f
162	1978	1	103	The Buddy Holly Story	Buddy Holly	f
163	1978	1	70	The Deer Hunter	Michael	f
164	1978	1	104	The Boys from Brazil	Ezra Lieberman	f
165	1978	1	89	Coming Home	Luke Martin	t
166	1977	1	105	Annie Hall	Alvy Singer	f
167	1977	1	106	Equus	Dr. Martin Dysart	f
168	1977	1	58	The Goodbye Girl	Elliot Garfield	t
169	1977	1	83	A Special Day	Gabriele	f
170	1977	1	63	Saturday Night Fever	Tony Manero	f
171	1976	1	70	Taxi Driver	Travis Bickle	f
172	1976	1	107	Network	Howard Beale	t
173	1976	1	108	Seven Beauties	Pasqualino Frafuso	f
174	1976	1	109	Network	Max Schumacher	f
175	1976	1	110	Rocky	Rocky Balboa	f
176	1975	1	111	The Sunshine Boys	Willy Clark	f
177	1975	1	37	One Flew over the Cuckoo's Nest	Randle Patrick McMurphy	t
178	1975	1	67	Dog Day Afternoon	Sonny	f
179	1975	1	112	The Man in the Glass Booth	Arthur Goldman	f
180	1975	1	113	Give 'em Hell, Harry!	Harry S. Truman	f
181	1974	1	114	Harry and Tonto	Harry	t
182	1974	1	91	Murder on the Orient Express	Hercule Poirot	f
183	1974	1	53	Lenny	Lenny Bruce	f
184	1974	1	37	Chinatown	J. J. Gittes	f
185	1974	1	67	The Godfather Part II	Michael Corleone	f
186	1973	1	115	Last Tango in Paris	Paul	f
187	1973	1	96	Save the Tiger	Harry Stoner	t
188	1973	1	37	The Last Detail	Signalman First Class Buddusky	f
189	1973	1	67	Serpico	Frank Serpico	f
190	1973	1	116	The Sting	Johnny Hooker	f
191	1972	1	115	The Godfather	Don Vito Corleone	t
192	1972	1	36	Sleuth	Milo Tindale	f
193	1972	1	104	Sleuth	Andrew Wyke	f
194	1972	1	20	The Ruling Class	Jack, 14th Earl of Gurney	f
195	1972	1	117	Sounder	Nathan Lee Morgan	f
196	1971	1	107	Sunday Bloody Sunday	Dr. Daniel Hirsh	f
197	1971	1	78	The French Connection	Jimmy 'Popeye' Doyle	t
198	1971	1	111	Kotch	Joseph P. Kotcher	f
199	1971	1	118	The Hospital	Dr. Herbert Bock	f
200	1971	1	119	Fiddler on the Roof	Tevye	f
201	1970	1	120	I Never Sang for My Father	Tom Garrison	f
202	1970	1	121	The Great White Hope	Jack Jefferson	f
203	1970	1	37	Five Easy Pieces	Robert Eroica Dupea	f
204	1970	1	122	Love Story	Oliver Barrett IV	f
205	1970	1	118	Patton	General George S. Patton, Jr.	t
206	1969	1	106	Anne of the Thousand Days	King Henry VIII	f
207	1969	1	53	Midnight Cowboy	Ratso Rizzo	f
208	1969	1	20	Goodbye, Mr. Chips	Arthur Chipping	f
209	1969	1	89	Midnight Cowboy	Joe Buck	f
210	1969	1	123	True Grit	Rooster Cogburn	t
211	1968	1	124	The Heart Is a Lonely Hunter	Singer	f
212	1968	1	125	The Fixer	Yakov Bok	f
213	1968	1	126	Oliver!	Fagin	f
214	1968	1	20	The Lion in Winter	King Henry II	f
215	1968	1	127	Charly	Charly Gordon	t
216	1967	1	69	Bonnie and Clyde	Clyde Barrow	f
217	1967	1	53	The Graduate	Ben Braddock	f
218	1967	1	62	Cool Hand Luke	Luke Jackson	f
219	1967	1	128	In the Heat of the Night	Police Chief Bill Gillespie	t
220	1967	1	129	Guess Who's Coming to Dinner	Matt Drayton	f
221	1966	1	124	The Russians Are Coming The Russians Are Coming	Rozanov	f
222	1966	1	106	Who's Afraid of Virginia Woolf?	George	f
223	1966	1	36	Alfie	Alfie	f
224	1966	1	130	The Sand Pebbles	Jake Holman	f
225	1966	1	131	A Man for All Seasons	Sir Thomas More	t
226	1965	1	106	The Spy Who Came In from the Cold	Alec Leamas	f
227	1965	1	132	Cat Ballou	Kid Shelleen/Tim Strawn	t
228	1965	1	104	Othello	Othello	f
229	1965	1	128	The Pawnbroker	Sol Nazerman	f
230	1965	1	133	Ship of Fools	Dr. Schumann	f
231	1964	1	106	Becket	Thomas Becket	f
232	1964	1	134	My Fair Lady	Professor Henry Higgins	t
233	1964	1	20	Becket	King Henry II	f
234	1964	1	135	Zorba the Greek	Alexis Zorba	f
235	1964	1	102	Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb	Group Captain Lionel Mandrake/President Muffley/Dr. Strangelove	f
236	1963	1	91	Tom Jones	Tom Jones	f
237	1963	1	74	This Sporting Life	Frank Machin	f
238	1963	1	134	Cleopatra	Julius Caesar	f
239	1963	1	62	Hud	Hud Bannon	f
240	1963	1	136	Lilies of the Field	Homer Smith	t
241	1962	1	98	Birdman of Alcatraz	Robert Stroud	f
242	1962	1	96	Days of Wine and Roses	Joe Clay	f
243	1962	1	83	Divorce--Italian Style	Ferdinando Cefalu	f
244	1962	1	20	Lawrence of Arabia	T.E. Lawrence	f
245	1962	1	137	To Kill a Mockingbird	Atticus Finch	t
246	1961	1	138	Fanny	César	f
247	1961	1	62	The Hustler	Eddie Felson	f
248	1961	1	112	Judgment at Nuremberg	Hans Rolfe	t
249	1961	1	129	Judgment at Nuremberg	Judge Dan Haywood	f
250	1961	1	139	The Mark	Jim Fuller	f
251	1960	1	140	Sons and Lovers	Morel	f
252	1960	1	98	Elmer Gantry	Elmer Gantry	t
253	1960	1	96	The Apartment	C.C. 'Bud' Baxter	f
254	1960	1	104	The Entertainer	Archie Rice	f
255	1960	1	129	Inherit the Wind	Henry Drummond	f
256	1959	1	141	Room at the Top	Joe Lampton	f
257	1959	1	142	Ben-Hur	Judah Ben-Hur	t
258	1959	1	96	Some Like It Hot	Jerry/Daphne	f
259	1959	1	143	The Last Angry Man	Dr. Sam Abelman	f
260	1959	1	144	Anatomy of a Murder	Paul Biegler	f
261	1958	1	145	The Defiant Ones	John 'Joker' Jackson	f
262	1958	1	62	Cat on a Hot Tin Roof	Brick Pollitt	f
263	1958	1	146	Separate Tables	Major Pollock	t
264	1958	1	136	The Defiant Ones	Noah Cullen	f
265	1958	1	129	The Old Man and the Sea	The Old Man	f
266	1957	1	115	Sayonara	Major Lloyd Gruver	f
267	1957	1	147	A Hatful of Rain	Polo	f
268	1957	1	148	The Bridge on the River Kwai	Colonel Nicholson	t
269	1957	1	149	Witness for the Prosecution	Sir Wilfrid Robarts	f
270	1957	1	135	Wild Is the Wind	Gino	f
271	1956	1	150	The King and I	The King	t
272	1956	1	151	Giant	Jett Rink	f
273	1956	1	152	Lust for Life	Vincent Van Gogh	f
274	1956	1	153	Giant	Bick Benedict	f
275	1956	1	154	Richard III	Richard III	f
276	1955	1	155	Marty	Marty Pilletti	t
277	1955	1	156	Love Me or Leave Me	Martin Snyder	f
278	1955	1	151	East of Eden	Cal Trask	f
279	1955	1	157	The Man with the Golden Arm	Frankie	f
280	1955	1	129	Bad Day at Black Rock	John J. Macreedy	f
281	1954	1	158	The Caine Mutiny	Captain Queeg	f
282	1954	1	115	On the Waterfront	Terry Malloy	t
283	1954	1	159	The Country Girl	Frank Elgin	f
284	1954	1	160	A Star Is Born	Norman Maine/Alfred Hinkel	f
285	1954	1	161	Adventures of Robinson Crusoe	Robinson Crusoe	f
286	1953	1	115	Julius Caesar	Marc Antony	f
287	1953	1	106	The Robe	Marcellus Gallio	f
288	1953	1	162	From Here to Eternity	Robert E. Lee Prewitt	f
289	1953	1	109	Stalag 17	Sefton	t
290	1953	1	98	From Here to Eternity	Sgt. Milton Warden	f
291	1952	1	115	Viva Zapata!	Emiliano Zapata	f
292	1952	1	163	High Noon	Will Kane	t
293	1952	1	152	The Bad and the Beautiful	Jonathan Shields	f
294	1952	1	164	Moulin Rouge	Toulouse-Lautrec	f
295	1952	1	148	The Lavender Hill Mob	Holland	f
296	1951	1	158	The African Queen	Charlie Allnut	t
297	1951	1	115	A Streetcar Named Desire	Stanley Kowalski	f
298	1951	1	162	A Place in the Sun	George Eastman	f
299	1951	1	165	Bright Victory	Larry Levins	f
300	1951	1	166	Death of a Salesman	Willy Loman	f
301	1950	1	167	The Magnificent Yankee	Oliver Wendell Holmes	f
302	1950	1	164	Cyrano de Bergerac	Cyrano de Bergerac	t
303	1950	1	109	Sunset Blvd.	Joe Gillis	f
304	1950	1	144	Harvey	Elwood P. Dowd	f
305	1950	1	129	Father of the Bride	Stanley T. Banks	f
306	1949	1	168	All the King's Men	Willie Stark	t
307	1949	1	152	Champion	Midge Kelly	f
308	1949	1	137	Twelve O'Clock High	General Savage	f
309	1949	1	169	The Hasty Heart	Lachie	f
310	1949	1	123	Sands of Iwo Jima	Sergeant John M. Stryker	f
311	1948	1	170	Johnny Belinda	Dr. Robert Richardson	f
312	1948	1	162	The Search	Ralph Stevenson	f
313	1948	1	171	When My Baby Smiles at Me	Skid	f
314	1948	1	104	Hamlet	Hamlet	t
315	1948	1	172	Sitting Pretty	Lynn Belvedere	f
316	1947	1	173	A Double Life	Anthony John	t
317	1947	1	174	Body and Soul	Charlie Davis	f
318	1947	1	137	Gentleman's Agreement	Phil Green	f
319	1947	1	175	Life with Father	Clarence Day	f
320	1947	1	176	Mourning Becomes Electra	Orin Mannon	f
321	1946	1	166	The Best Years of Our Lives	Al Stephenson	t
322	1946	1	104	Henry V	Henry V	f
323	1946	1	177	The Jolson Story	Al Jolson	f
324	1946	1	137	The Yearling	Pa Baxter	f
325	1946	1	144	It's a Wonderful Life	George Bailey	f
326	1945	1	159	The Bells of St. Mary's	Father O'Malley	f
327	1945	1	178	Anchors Aweigh	Joseph Brady	f
328	1945	1	179	The Lost Weekend	Don Birnam	t
329	1945	1	137	The Keys of the Kingdom	Father Francis Chisholm	f
330	1945	1	180	A Song to Remember	Frederick Chopin	f
331	1944	1	138	Gaslight	Gregory Anton	f
332	1944	1	159	Going My Way	Father O'Malley	t
333	1944	1	181	Going My Way	Father Fitzgibbon	f
334	1944	1	182	None but the Lonely Heart	Ernie Mott	f
335	1944	1	183	Wilson	Woodrow Wilson	f
336	1943	1	158	Casablanca	Rick Blane	f
337	1943	1	163	For Whom the Bell Tolls	Robert Jordan	f
338	1943	1	184	Watch on the Rhine	Kurt Muller	t
339	1943	1	185	Madame Curie	Pierre Curie	f
340	1943	1	186	The Human Comedy	Homer Macauley	f
341	1942	1	156	Yankee Doodle Dandy	George M. Cohan	t
342	1942	1	173	Random Harvest	Charles Ranier	f
343	1942	1	163	The Pride of the Yankees	Lou Gehrig	f
344	1942	1	185	Mrs. Miniver	Clem Miniver	f
345	1942	1	187	The Pied Piper	Howard	f
346	1941	1	163	Sergeant York	Alvin C. York	t
347	1941	1	182	Penny Serenade	Roger Adams	f
348	1941	1	188	All That Money Can Buy	Mr. Scratch	f
349	1941	1	189	Here Comes Mr. Jordan	Joe Pendleton	f
350	1941	1	190	Citizen Kane	Charles Foster Kane	f
351	1940	1	191	The Great Dictator	Hynkel, Dictator of Tomania	f
352	1940	1	97	The Grapes of Wrath	Tom Joad	f
353	1940	1	192	Abe Lincoln in Illinois	Abraham Lincoln	f
354	1940	1	104	Rebecca	Maxim de Winter	f
355	1940	1	144	The Philadelphia Story	Mike Connor	t
356	1939	1	193	Goodbye, Mr. Chips	Mr. Chips	t
357	1939	1	194	Gone with the Wind	Rhett Butler	f
358	1939	1	104	Wuthering Heights	Heathcliff	f
359	1939	1	186	Babes in Arms	Mickey Moran	f
360	1939	1	144	Mr. Smith Goes to Washington	Jefferson Smith	f
361	1938	1	138	Algiers	Pepe Le Moko	f
362	1938	1	156	Angels with Dirty Faces	Rocky Sullivan	f
363	1938	1	193	The Citadel	Andrew Mason	f
364	1938	1	195	Pygmalion	Professor Henry Higgins	f
365	1938	1	129	Boys Town	Father Flanagan	t
366	1937	1	138	Conquest	Napoleon Bonaparte	f
367	1937	1	166	A Star Is Born	Norman Maine (Alfred Hinkel)	f
368	1937	1	189	Night Must Fall	Danny	f
369	1937	1	143	The Life of Emile Zola	Emile Zola	f
370	1937	1	129	Captains Courageous	Manuel	t
371	1936	1	163	Mr. Deeds Goes to Town	Longfellow Deeds	f
372	1936	1	188	Dodsworth	Sam Dodsworth	f
373	1936	1	143	The Story of Louis Pasteur	Louis Pasteur	t
374	1936	1	175	My Man Godfrey	Godfrey Parks	f
375	1936	1	129	San Francisco	Father Tim Mullen	f
376	1935	1	194	Mutiny on the Bounty	Fletcher Christian	f
377	1935	1	149	Mutiny on the Bounty	Captain Bligh	f
378	1935	1	196	The Informer	Gypo Nolan	t
379	1935	1	143	Black Fury	Joe Radek	f
380	1935	1	197	Mutiny on the Bounty	Roger Byam	f
381	1934	1	194	It Happened One Night	Peter Warne	t
382	1934	1	198	The Affairs of Cellini	Allesandro, Duke of Florence	f
383	1934	1	175	The Thin Man	Nick Charles	f
384	1932	1	195	Berkeley Square	Peter Standish	f
385	1932	1	149	The Private Life of Henry VIII	Henry VIII	t
386	1932	1	143	I Am a Fugitive from a Chain Gang	James Allen	f
387	1931	1	199	The Champ	Champ	t
388	1931	1	200	The Guardsman	The Actor	f
389	1931	1	166	Dr. Jekyll and Mr. Hyde	Dr. Henry Jekyll/Mr. Hyde	t
390	1930	1	201	A Free Soul	Stephen Ashe	t
391	1930	1	202	Skippy	Skippy Skinner	f
392	1930	1	203	Cimarron	Yancey Cravat	f
393	1930	1	166	The Royal Family of Broadway	Tony Cavendish	f
394	1930	1	204	The Front Page	Walter Burns	f
395	1929	1	205	Disraeli	Benjamin Disraeli	t
396	1929	1	205	The Green Goddess	Oxonian, the Rajah of Rukh	f
397	1929	1	199	The Big House	'Machine Gun' Butch Schmidt	f
398	1929	1	206	The Big Pond	Pierre Mirande	f
399	1929	1	173	Bulldog Drummond	Hugh 'Bulldog' Drummond	f
400	1929	1	207	The Rogue Song	Yegor	f
401	1928	1	208	Thunderbolt	Thunderbolt Jim Lang	f
402	1928	1	209	In Old Arizona	The Cisco Kid	t
403	1928	1	210	Alibi	No. 1065, Chick Williams	f
404	1928	1	143	The Valiant	James Dyke	f
405	1928	1	211	The Patriot	Count Pahlen	f
406	1927	1	212	The Noose	Nickie Elkins	f
407	1927	1	213	The Last Command	General Dolgorucki [Grand Duke Sergius Alexander]	t
408	2010	2	214	The Fighter	Dicky Eklund	t
409	2010	2	215	Winter's Bone	Teardrop	f
410	2010	2	8	The Town	James Coughlin	f
411	2010	2	216	The Kids Are All Right	Paul	f
412	2010	2	43	The King's Speech	Lionel Logue	f
413	2009	2	50	Invictus	Francois Pienaar	f
414	2009	2	56	The Messenger	Captain Tony Stone	f
415	2009	2	217	The Last Station	Tolstoy	f
416	2009	2	218	The Lovely Bones	George Harvey	f
417	2009	2	219	Inglourious Basterds	Col. Hans Landa	t
418	2008	2	220	Milk	Dan White	f
419	2008	2	221	Tropic Thunder	Kirk Lazarus	f
420	2008	2	23	Doubt	Father Brendan Flynn	f
421	2008	2	25	The Dark Knight	Joker	t
422	2008	2	222	Revolutionary Road	John Givings	f
423	2007	2	223	The Assassination of Jesse James by the Coward Robert Ford	Robert Ford	f
424	2007	2	1	No Country for Old Men	Anton Chigurh	t
425	2007	2	23	Charlie Wilson's War	Gust Avrakotos	f
426	2007	2	224	Into the Wild	Ron Franz	f
427	2007	2	40	Michael Clayton	Arthur Edens	f
428	2006	2	124	Little Miss Sunshine	Grandpa	t
429	2006	2	225	Little Children	Ronnie J. McGorvey	f
430	2006	2	226	Blood Diamond	Solomon Vandy	f
431	2006	2	227	Dreamgirls	James 'Thunder' Early	f
432	2006	2	228	The Departed	Dignam	f
433	2005	2	6	Syriana	Bob Barnes	t
434	2005	2	229	Crash	Officer Ryan	f
435	2005	2	230	Cinderella Man	Joe Gould	f
436	2005	2	231	Brokeback Mountain	Jack Twist	f
437	2005	2	82	A History of Violence	Richie Cusack	f
438	2004	2	232	The Aviator	Senator Ralph Owen Brewster	f
439	2004	2	233	Sideways	Jack	f
440	2004	2	30	Collateral	Max	f
441	2004	2	7	Million Dollar Baby	Eddie Scrap-Iron Dupris	t
442	2004	2	234	Closer	Larry	f
443	2003	2	235	The Cooler	Shelly Kaplow	f
444	2003	2	236	21 Grams	Jack Jordan	f
445	2003	2	226	In America	Mateo	f
446	2003	2	237	Mystic River	Dave Boyle	t
447	2003	2	238	The Last Samurai	Katsumoto	f
448	2002	2	239	Adaptation	John Laroche	t
449	2002	2	42	The Hours	Richard Brown	f
450	2002	2	62	Road to Perdition	John Rooney	f
451	2002	2	240	Chicago	Amos Hart	f
452	2002	2	241	Catch Me If You Can	Frank Abagnale	f
453	2001	2	242	Iris	John Bayley	t
454	2001	2	243	Training Day	Jake	f
455	2001	2	31	Sexy Beast	Don Logan	f
456	2001	2	47	The Lord of the Rings: The Fellowship of the Ring	Gandalf	f
457	2001	2	89	Ali	Howard Cosell	f
458	2000	2	2	The Contender	President Jackson Evans	f
459	2000	2	244	Shadow of the Vampire	Max Schreck	f
460	2000	2	236	Traffic	Javier Rodriguez	t
461	2000	2	91	Erin Brockovich	Ed Masry	f
462	2000	2	26	Gladiator	Commodus	f
463	1999	2	36	The Cider House Rules	Dr. Wilbur Larch	t
464	1999	2	54	Magnolia	Frank T.J. Mackey	f
465	1999	2	245	The Green Mile	John Coffey	f
466	1999	2	32	The Talented Mr. Ripley	Dickie Greenleaf	f
467	1999	2	246	The Sixth Sense	Cole Sear	f
468	1998	2	247	Affliction	Glen Whitehouse	t
469	1998	2	51	A Civil Action	Jerome Facher	f
470	1998	2	42	The Truman Show	Christof	f
471	1998	2	43	Shakespeare in Love	Philip Henslowe	f
472	1998	2	57	A Simple Plan	Jacob	f
473	1997	2	248	Jackie Brown	Max Cherry	f
474	1997	2	59	Amistad	John Quincy Adams	f
475	1997	2	249	As Good as It Gets	Simon Bishop	f
476	1997	2	250	Boogie Nights	Jack Horner	f
477	1997	2	71	Good Will Hunting	Sean McGuire	t
478	1996	2	251	Jerry Maguire	Rod Tidwell	t
479	1996	2	252	Fargo	Jerry Lundegaard	f
480	1996	2	253	Shine	Peter Helfgott	f
481	1996	2	49	Primal Fear	Aaron Stampler	f
482	1996	2	86	Ghosts of Mississippi	Byron De La Beckwith	f
483	1995	2	254	Babe	Farmer Hoggett	f
484	1995	2	42	Apollo 13	Gene Kranz	f
485	1995	2	12	12 Monkeys	Jeffrey Goines	f
486	1995	2	255	Rob Roy	Archibald Cunningham	f
487	1995	2	45	The Usual Suspects	Roger 'Verbal' Kint	t
488	1994	2	256	Pulp Fiction	Jules Winnfield	f
489	1994	2	257	Ed Wood	Bela Lugosi	t
490	1994	2	258	Bullets over Broadway	Cheech	f
491	1994	2	131	Quiz Show	Mark Van Doren	f
492	1994	2	259	Forrest Gump	Lieutenant Dan	f
493	1993	2	18	What's Eating Gilbert Grape	Arnie Grape	f
494	1993	2	55	Schindler's List	Amon Goeth	f
495	1993	2	16	The Fugitive	Samuel Gerard	t
496	1993	2	260	In the Line of Fire	Mitch Leary	f
497	1993	2	261	In the Name of the Father	Giuseppe Conlon	f
498	1992	2	262	The Crying Game	Dil	f
499	1992	2	78	Unforgiven	Little Bill Daggett	t
500	1992	2	37	A Few Good Men	Col. Nathan R. Jessep	f
501	1992	2	67	Glengarry Glen Ross	Ricky Roma	f
502	1992	2	263	Mr. Saturday Night	Stan	f
503	1991	2	16	JFK	Clay Shaw	f
504	1991	2	264	Bugsy	Mickey Cohen	f
505	1991	2	31	Bugsy	Meyer Lansky	f
506	1991	2	265	Barton Fink	Jack Lipnick	f
507	1991	2	266	City Slickers	Curly	t
508	1990	2	267	Longtime Companion	David	f
509	1990	2	268	The Godfather, Part III	Vincent Mancini	f
510	1990	2	269	Dances With Wolves	Kicking Bird	f
511	1990	2	67	Dick Tracy	Big Boy Caprice	f
512	1990	2	270	Good Fellas	Tommy DeVito	t
513	1989	2	271	Do the Right Thing	Sal	f
514	1989	2	272	Driving Miss Daisy	Boolie Werthan	f
515	1989	2	115	A Dry White Season	McKenzie	f
516	1989	2	257	Crimes and Misdemeanors	Judah Rosenthal	f
517	1989	2	39	Glory	Trip	t
518	1988	2	148	Little Dorrit	William Dorrit	f
519	1988	2	273	A Fish Called Wanda	Otto	t
520	1988	2	257	Tucker The Man and His Dream	Abe	f
521	1988	2	274	Running on Empty	Danny Pope	f
522	1988	2	275	Married to the Mob	Tony 'the Tiger' Russo	f
523	1987	2	276	Broadcast News	Aaron Altman	f
524	1987	2	277	The Untouchables	Jim Malone	t
525	1987	2	7	Street Smart	Fast Black	f
526	1987	2	278	Moonstruck	Cosmo Castorini	f
527	1987	2	39	Cry Freedom	Steve Biko	f
528	1986	2	279	Platoon	Sgt. Barnes	f
529	1986	2	36	Hannah and Her Sisters	Elliot	t
530	1986	2	244	Platoon	Sgt. Elias	f
531	1986	2	280	A Room with a View	Mr. Emerson	f
532	1986	2	281	Hoosiers	Shooter	f
533	1985	2	282	Cocoon	Art Selwyn	t
534	1985	2	283	Out of Africa	Bror	f
535	1985	2	284	Prizzi's Honor	Don Corrado Prizzi	f
536	1985	2	285	Jagged Edge	Sam Ransom	f
537	1985	2	286	Runaway Train	Buck	f
538	1984	2	287	A Soldier's Story	Sgt. Waters	f
539	1984	2	260	Places in the Heart	Mr. Will	f
540	1984	2	288	The Karate Kid	Miyagi	f
541	1984	2	289	The Killing Fields	Dith Pran	t
542	1984	2	290	Greystoke: The Legend of Tarzan, Lord of the Apes	The Sixth Earl of Greystoke	f
543	1983	2	291	To Be or Not to Be	Col. Erhardt	f
544	1983	2	292	Terms of Endearment	Sam Burns	f
545	1983	2	37	Terms of Endearment	Garrett Breedlove	t
546	1983	2	293	The Right Stuff	Chuck Yeager	f
547	1983	2	294	Cross Creek	Marsh Turner	f
548	1982	2	291	The Best Little Whorehouse in Texas	The Governor	f
549	1982	2	295	An Officer and a Gentleman	Sgt. Emil Foley	t
550	1982	2	292	The World According to Garp	Roberta Muldoon	f
551	1982	2	160	The Verdict	Edward Concannon	f
552	1982	2	296	Victor/Victoria	Toddy	f
553	1981	2	297	Only When I Laugh	Jimmy Perry	f
554	1981	2	298	Arthur	Hobson	t
555	1981	2	299	Chariots of Fire	Sam Mussabini	f
556	1981	2	37	Reds	Eugene O'Neill	f
557	1981	2	300	Ragtime	Coalhouse Walker, Jr.	f
558	1980	2	301	Ordinary People	Dr. Berger	f
559	1980	2	302	Ordinary People	Conrad Jarrett	t
560	1980	2	303	The Great Santini	Ben Meechum	f
561	1980	2	270	Raging Bull	Joey LaMotta	f
562	1980	2	304	Melvin and Howard	Howard Hughes	f
563	1979	2	120	Being There	Benjamin Rand	t
564	1979	2	51	Apocalypse Now	Lieutenant Colonel Kilgore	f
565	1979	2	305	The Rose	Dyer	f
566	1979	2	306	Kramer vs. Kramer	Billy Kramer	f
567	1979	2	186	The Black Stallion	Henry Dailey	f
568	1978	2	307	Coming Home	Captain Bob Hyde	f
569	1978	2	44	Comes a Horseman	Dodger	f
570	1978	2	100	Midnight Express	Max	f
571	1978	2	241	The Deer Hunter	Nick	t
572	1978	2	308	Heaven Can Wait	Max Corkle	f
573	1977	2	309	The Turning Point	Yuri Kopeikine	f
574	1977	2	310	Equus	Alan Strang	f
575	1977	2	148	Star Wars	Ben 'Obi-Wan' Kenobi	f
576	1977	2	304	Julia	Dashiell Hammett	t
577	1977	2	112	Julia	Johann	f
578	1976	2	311	Network	Arthur Jensen	f
579	1976	2	312	Rocky	Mickey	f
580	1976	2	104	Marathon Man	Szell	f
581	1976	2	304	All the President's Men	Ben Bradlee	t
582	1976	2	313	Rocky	Paulie	f
583	1975	2	314	The Sunshine Boys	Al Lewis	t
584	1975	2	315	One Flew over the Cuckoo's Nest	Billy Bibbit	f
585	1975	2	312	The Day of the Locust	Harry	f
586	1975	2	316	Dog Day Afternoon	Leon	f
587	1975	2	308	Shampoo	Lester Carr	f
588	1974	2	317	The Towering Inferno	Harlee Claiborne	f
589	1974	2	2	Thunderbolt and Lightfoot	Lightfoot	f
590	1974	2	70	The Godfather Part II	Vito Corleone	t
591	1974	2	318	The Godfather Part II	Frankie Pentangeli	f
592	1974	2	319	The Godfather Part II	Hyman Roth	f
593	1973	2	278	Bang the Drum Slowly	Dutch Schnell	f
594	1973	2	320	Save the Tiger	Phil Greene	f
595	1973	2	321	The Paper Chase	Professor Kingsfield	t
596	1973	2	322	The Exorcist	Father Damian Karras	f
597	1973	2	323	The Last Detail	Meadows	f
598	1972	2	324	The Heartbreak Kid	Mr. Corcoran	f
599	1972	2	325	The Godfather	Sonny Corleone	f
600	1972	2	51	The Godfather	Tom Hagen	f
601	1972	2	326	Cabaret	The Master of Ceremonies	t
602	1972	2	67	The Godfather	Michael Corleone	f
603	1971	2	2	The Last Picture Show	Duane Jackson	f
604	1971	2	327	Fiddler on the Roof	Motel	f
605	1971	2	328	Sometimes a Great Notion	Joe Ben Stamper	f
606	1971	2	329	The Last Picture Show	Sam the Lion	t
607	1971	2	101	The French Connection	Buddy Russo	f
608	1970	2	330	Lovers and Other Strangers	Frank Vecchio	f
609	1970	2	331	Little Big Man	Old Lodge Skins	f
610	1970	2	78	I Never Sang for My Father	Gene Garrison	f
611	1970	2	332	Love Story	Phil Cavilleri	f
612	1970	2	333	Ryan's Daughter	Michael	t
613	1969	2	334	The Reivers	Ned McCaslin	f
614	1969	2	335	Bob & Carol & Ted & Alice	Ted Henderson	f
615	1969	2	37	Easy Rider	George Hanson	f
616	1969	2	336	Anne of the Thousand Days	Cardinal Wolsey	f
617	1969	2	337	They Shoot Horses, Don't They?	Rocky	t
618	1968	2	338	The Subject Was Roses	John Cleary	t
619	1968	2	339	Faces	Chet	f
620	1968	2	340	Star!	Noel Coward	f
621	1968	2	341	Oliver!	The Artful Dodger	f
622	1968	2	342	The Producers	Leo Bloom	f
623	1967	2	343	The Dirty Dozen	Victor Franko	f
624	1967	2	78	Bonnie and Clyde	Buck Barrow	f
625	1967	2	344	Guess Who's Coming to Dinner	Monsignor Ryan	f
626	1967	2	345	Cool Hand Luke	Dragline	t
627	1967	2	346	Bonnie and Clyde	C.W. Moss	f
628	1966	2	347	The Sand Pebbles	Po-Han	f
629	1966	2	160	Georgy Girl	James Leamington	f
630	1966	2	111	The Fortune Cookie	Willie Gingrich	t
631	1966	2	348	Who's Afraid of Virginia Woolf?	Nick	f
632	1966	2	349	A Man for All Seasons	King Henry VIII	f
633	1965	2	350	A Thousand Clowns	Arnold Burns	t
634	1965	2	351	The Flight of the Phoenix	Crow	f
635	1965	2	95	Doctor Zhivago	Pasha Antipov/Strelnikoff	f
636	1965	2	352	Ship of Fools	Glocken	f
637	1965	2	353	Othello	Iago	f
638	1964	2	298	Becket	King Louis VII of France	f
639	1964	2	354	My Fair Lady	Alfred P. Doolittle	f
640	1964	2	355	Seven Days in May	Senator Raymond Clark	f
641	1964	2	356	The Best Man	Art Hockstader	f
642	1964	2	357	Topkapi	Arthur Simpson	t
643	1963	2	358	Twilight of Honor	Ben Brown	f
644	1963	2	359	Captain Newman, M.D.	Corp. Jim Tompkins	f
645	1963	2	120	Hud	Homer Bannon	t
646	1963	2	360	Tom Jones	Squire Western	f
647	1963	2	361	The Cardinal	Cardinal Glennon	f
648	1962	2	362	Sweet Bird of Youth	Tom 'Boss' Finley	t
649	1962	2	363	What Ever Happened to Baby Jane?	Edwin Flagg	f
650	1962	2	364	Birdman of Alcatraz	Feto Gomez	f
651	1962	2	365	Lawrence of Arabia	Sherif Ali ibn el Kharish	f
652	1962	2	366	Billy Budd	Billy Budd	f
653	1961	2	367	West Side Story	Bernardo	t
654	1961	2	162	Judgment at Nuremberg	Rudolph Peterson	f
655	1961	2	368	Pocketful of Miracles	Joy Boy	f
656	1961	2	369	The Hustler	Minnesota Fats	f
657	1961	2	118	The Hustler	Bert Gordon	f
658	1960	2	368	Murder, Inc.	Abe Reles	f
659	1960	2	370	The Apartment	Dr. Dreyfuss	f
660	1960	2	371	Exodus	Dov Landau	f
661	1960	2	357	Spartacus	Batiatus	t
662	1960	2	372	The Alamo	Beekeeper	f
663	1959	2	360	Ben-Hur	Sheik Ilderim	t
664	1959	2	373	Anatomy of a Murder	Parnell McCarthy	f
665	1959	2	118	Anatomy of a Murder	Claude Dancer	f
666	1959	2	374	The Young Philadelphians	Chester Gwynn	f
667	1959	2	375	The Diary of Anne Frank	Mr. Dussell	f
668	1958	2	376	The Defiant Ones	Sheriff Max Muller	f
669	1958	2	377	The Brothers Karamazov	Fyodor Karamazov	f
670	1958	2	378	The Big Country	Rufus Hannassey	t
671	1958	2	165	Some Came Running	Frank Hirsh	f
672	1958	2	337	Teacher's Pet	Dr. Hugo Pine	f
673	1957	2	379	Sayonara	Joe Kelly	t
674	1957	2	380	A Farewell to Arms	Major Alessandro Rinaldi	f
675	1957	2	381	The Bridge on the River Kwai	Saito	f
676	1957	2	165	Peyton Place	Lucas Cross	f
677	1957	2	382	Peyton Place	Norman Page	f
678	1956	2	383	Bus Stop	Bo	f
679	1956	2	384	Friendly Persuasion	Josh Birdwell	f
680	1956	2	135	Lust for Life	Paul Gauguin	t
681	1956	2	186	The Bold and the Brave	Dooley	f
682	1956	2	385	Written on the Wind	Kyle Hadley	f
683	1955	2	165	Trial	Barney Castle	f
684	1955	2	96	Mister Roberts	Ensign Pulver	t
685	1955	2	386	Marty	Angie	f
686	1955	2	371	Rebel without a Cause	Plato	f
687	1955	2	373	Picnic	Howard Bevans	f
688	1954	2	377	On the Waterfront	Johnny Friendly	f
689	1954	2	387	On the Waterfront	Father Barry	f
690	1954	2	355	The Barefoot Contessa	Oscar Muldoon	t
691	1954	2	128	On the Waterfront	Charles Malloy	f
692	1954	2	388	The Caine Mutiny	Captain DeVriess	f
693	1953	2	324	Roman Holiday	Irving Radovich	f
694	1953	2	389	Shane	Joey Starrett	f
695	1953	2	266	Shane	Wilson	f
696	1953	2	157	From Here to Eternity	Angelo Maggio	t
697	1953	2	390	Stalag 17	Stosh/'Animal'	f
698	1952	2	106	My Cousin Rachel	Philip Ashley	f
699	1952	2	391	The Big Sky	Zeb Callaway	f
700	1952	2	196	The Quiet Man	Red Will Danaher	f
701	1952	2	266	Sudden Fear	Lester Blaine	f
702	1952	2	135	Viva Zapata!	Eufemio Zapata	t
703	1951	2	392	Quo Vadis	Petronius	f
704	1951	2	393	Death of a Salesman	Biff Loman	f
705	1951	2	387	A Streetcar Named Desire	Mitch	t
706	1951	2	357	Quo Vadis	Nero	f
707	1951	2	337	Come Fill the Cup	Boyd Copeland	f
708	1950	2	394	Broken Arrow	Cochise	f
709	1950	2	395	Mister 880	Skipper Miller	f
710	1950	2	396	The Asphalt Jungle	Dr. Erwin Riedenschneider	f
711	1950	2	397	All about Eve	Addison De Witt	t
712	1950	2	398	Sunset Blvd.	Max Von Mayerling	f
713	1949	2	399	All the King's Men	Jack Burden	f
714	1949	2	400	Twelve O'Clock High	Major Stovall	t
715	1949	2	165	Champion	Connie Kelly	f
716	1949	2	290	The Heiress	Dr. Austin Sloper	f
717	1949	2	113	Battleground	Kinnie	f
718	1948	2	401	Johnny Belinda	Black McDonald	f
719	1948	2	164	Joan of Arc	The Dauphin, Charles VIII	f
720	1948	2	402	I Remember Mama	Uncle Chris	f
721	1948	2	188	The Treasure of the Sierra Madre	Howard	t
722	1948	2	344	The Luck of the Irish	Horace	f
723	1947	2	401	The Farmer's Daughter	Clancy	f
724	1947	2	403	Ride the Pink Horse	Pancho	f
725	1947	2	395	Miracle on 34th Street	Kris Kringle	t
726	1947	2	404	Crossfire	Montgomery	f
727	1947	2	405	Kiss of Death	Tommy Udo	f
728	1946	2	406	The Green Years	Alexander Gow	f
729	1946	2	407	The Jolson Story	Steve Martin	f
730	1946	2	408	Notorious	Alexander Sebastian	f
731	1946	2	409	The Best Years of Our Lives	Homer Parrish	t
732	1946	2	172	The Razor's Edge	Elliott Templeton	f
733	1945	2	410	Spellbound	Dr. Alex Brulov	f
734	1945	2	411	The Corn Is Green	Morgan Evans	f
735	1945	2	412	A Tree Grows in Brooklyn	Johnny Nolan	t
736	1945	2	413	G. I. Joe	Lieutenant Walker	f
737	1945	2	414	A Medal for Benny	Charlie Martini	f
738	1944	2	415	The Seventh Cross	Paul Roeder	f
739	1944	2	181	Going My Way	Father Fitzgibbon	t
740	1944	2	408	Mr. Skeffington	Job Skeffington	f
741	1944	2	172	Laura	Waldo Lydecker	f
742	1944	2	187	Since You Went Away	Colonel Smollett	f
743	1943	2	401	The Song of Bernadette	Dean Peyramale	f
744	1943	2	406	The More the Merrier	Benjamin Dingle	t
745	1943	2	414	Sahara	Giuseppe	f
746	1943	2	408	Casablanca	Captain Louis Renault	f
747	1943	2	416	For Whom the Bell Tolls	Pablo	f
748	1942	2	417	Wake Island	Smacksie Randall	f
749	1942	2	418	Johnny Eager	Jeff Hartnett	t
750	1942	2	188	Yankee Doodle Dandy	Jerry Cohan	f
751	1942	2	198	Tortilla Flat	The Pirate	f
752	1942	2	419	Mrs. Miniver	Mr. Ballard	f
753	1941	2	420	Sergeant York	Pastor Posier Pile	f
754	1941	2	406	The Devil and Miss Jones	John P. Merrick	f
755	1941	2	421	How Green Was My Valley	Mr. Morgan	t
756	1941	2	422	Here Comes Mr. Jordan	Max Corkle	f
757	1941	2	423	The Maltese Falcon	Kaspar Gutman	f
758	1940	2	424	Foreign Correspondent	Van Meer	f
759	1940	2	420	The Westerner	Judge Roy Bean	t
760	1940	2	425	They Knew What They Wanted	Joe, the Foreman	f
761	1940	2	426	The Great Dictator	Napaloni, Dictator of Bacteria	f
762	1940	2	427	The Letter	Howard Joyce	f
763	1939	2	428	Juarez	Emperor Maximilian von Habsburg	f
764	1939	2	429	Mr. Smith Goes to Washington	President of the Senate	f
765	1939	2	430	Beau Geste	Sergeant Markoff	f
766	1939	2	431	Stagecoach	Dr. Josiah Boone	t
767	1939	2	408	Mr. Smith Goes to Washington	Senator Joseph Paine	f
768	1938	2	420	Kentucky	Peter Goodwin	t
769	1938	2	174	Four Daughters	Mickey Borden	f
770	1938	2	432	Algiers	Regis	f
771	1938	2	433	Marie Antoinette	King Louis XVI	f
772	1938	2	434	If I Were King	Louis XI	f
773	1937	2	435	The Awful Truth	Daniel Leeson	f
774	1937	2	431	The Hurricane	Doctor Kersaint	f
775	1937	2	436	The Life of Emile Zola	Captain Alfred Dreyfus	t
776	1937	2	437	Lost Horizon	Chang	f
777	1937	2	438	Topper	Cosmo Topper	f
778	1936	2	439	My Man Godfrey	Carlo	f
779	1936	2	420	Come and Get It	Swan Bostrom	t
780	1936	2	440	Pigskin Parade	Amos Dodd	f
781	1936	2	434	Romeo and Juliet	Tybalt	f
782	1936	2	416	The General Died at Dawn	General Yang	f
783	2010	3	441	The Kids Are All Right	Nic	f
784	2010	3	442	Rabbit Hole	Becca	f
785	2010	3	443	Winter's Bone	Ree	f
786	2010	3	444	Black Swan	Nina Sayers/The Swan Queen	t
787	2010	3	445	Blue Valentine	Cindy	f
788	2009	3	446	The Blind Side	Leigh Anne Tuohy	t
789	2009	3	447	The Last Station	Sofya	f
790	2009	3	448	An Education	Jenny	f
791	2009	3	449	Precious: Based on the Novel 'Push' by Sapphire	Precious	f
792	2009	3	450	Julie & Julia	Julia Child	f
793	2008	3	451	Rachel Getting Married	Kym	f
794	2008	3	452	Changeling	Christine Collins	f
795	2008	3	453	Frozen River	Ray Eddy	f
796	2008	3	450	Doubt	Sister Aloysius Beauvier	f
797	2008	3	454	The Reader	Hanna Schmitz	t
798	2007	3	455	Elizabeth: The Golden Age	Queen Elizabeth I	f
799	2007	3	456	Away from Her	Fiona	f
800	2007	3	457	La Vie en Rose	Edith Piaf	t
801	2007	3	458	The Savages	Wendy Savage	f
802	2007	3	459	Juno	Juno MacGuff	f
803	2006	3	460	Volver	Raimunda	f
804	2006	3	461	Notes on a Scandal	Barbara Covett	f
805	2006	3	447	The Queen	The Queen	t
806	2006	3	450	The Devil Wears Prada	Miranda Priestly	f
807	2006	3	454	Little Children	Sarah Pierce	f
808	2005	3	461	Mrs. Henderson Presents	Mrs. Laura Henderson	f
809	2005	3	462	Transamerica	Bree	f
810	2005	3	463	Pride & Prejudice	Elizabeth Bennet	f
811	2005	3	464	North Country	Josey Aimes	f
812	2005	3	465	Walk the Line	June Carter	t
813	2004	3	441	Being Julia	Julia Lambert	f
814	2004	3	466	Maria Full of Grace	Maria	f
815	2004	3	467	Vera Drake	Vera	f
816	2004	3	468	Million Dollar Baby	Maggie Fitzgerald	t
817	2004	3	454	Eternal Sunshine of the Spotless Mind	Clementine Kruczynski	f
818	2003	3	469	Whale Rider	Paikea	f
819	2003	3	470	Something's Gotta Give	Erica Barry	f
820	2003	3	471	In America	Sarah	f
821	2003	3	464	Monster	Aileen Wuornos	t
822	2003	3	472	21 Grams	Cristina Peck	f
823	2002	3	473	Frida	Frida Kahlo	f
824	2002	3	442	The Hours	Virginia Woolf	t
825	2002	3	474	Unfaithful	Connie Sumner	f
826	2002	3	475	Far from Heaven	Cathy Whitaker	f
827	2002	3	476	Chicago	Roxie Hart	f
828	2001	3	477	Monster's Ball	Leticia Musgrove	t
829	2001	3	461	Iris	Iris Murdoch	f
830	2001	3	442	Moulin Rouge	Satine	f
831	2001	3	478	In the Bedroom	Ruth Fowler	f
832	2001	3	476	Bridget Jones's Diary	Bridget Jones	f
833	2000	3	479	The Contender	Laine Hanson	f
834	2000	3	480	Chocolat	Vianne Rocher	f
835	2000	3	481	Requiem for a Dream	Sara Goldfarb	f
836	2000	3	458	You Can Count on Me	Samantha 'Sammy' Prescott	f
837	2000	3	482	Erin Brockovich	Erin Brockovich	t
838	1999	3	441	American Beauty	Carolyn Burnham	f
839	1999	3	483	Tumbleweeds	Mary Jo Walker	f
840	1999	3	475	The End of the Affair	Sarah Miles	f
841	1999	3	450	Music of the Heart	Roberta Guaspari	f
842	1999	3	468	Boys Don't Cry	Brandon Teena/Teena Brandon	t
843	1998	3	455	Elizabeth	Queen Elizabeth I	f
844	1998	3	484	Central Station	Dora	f
845	1998	3	485	Shakespeare in Love	Viola De Lesseps	t
846	1998	3	450	One True Thing	Kate Gulden	f
847	1998	3	486	Hilary and Jackie	Jacqueline du Pré	f
848	1997	3	487	The Wings of the Dove	Kate Croy	f
849	1997	3	456	Afterglow	Phyllis Mann	f
850	1997	3	461	Mrs. Brown	Queen Victoria	f
851	1997	3	488	As Good as It Gets	Carol Connelly	t
852	1997	3	454	Titanic	Rose DeWitt Bukater	f
853	1996	3	489	Secrets & Lies	Cynthia	f
854	1996	3	470	Marvin's Room	Bessie	f
855	1996	3	490	Fargo	Marge Gunderson	t
856	1996	3	491	The English Patient	Katharine Clifton	f
857	1996	3	486	Breaking the Waves	Bess	f
858	1995	3	492	Dead Man Walking	Sister Helen Prejean	t
859	1995	3	493	Leaving Las Vegas	Sera	f
860	1995	3	494	Casino	Ginger McKenna	f
861	1995	3	450	The Bridges of Madison County	Francesca Johnson	f
862	1995	3	495	Sense and Sensibility	Elinor Dashwood	f
863	1994	3	496	Nell	Nell	f
864	1994	3	497	Blue Sky	Carly Marshall	t
865	1994	3	498	Tom & Viv	Viv	f
866	1994	3	499	Little Women	Jo March	f
867	1994	3	492	The Client	Reggie Love	f
868	1993	3	500	What's Love Got to Do with It	Tina Turner	f
869	1993	3	501	Six Degrees of Separation	Ouisa Kittredge	f
870	1993	3	502	The Piano	Ada	t
871	1993	3	495	The Remains of the Day	Miss Kenton	f
872	1993	3	503	Shadowlands	Joy Gresham	f
873	1992	3	504	Indochine	Eliane	f
874	1992	3	505	Passion Fish	May-Alice	f
875	1992	3	506	Love Field	Lurene Hallett	f
876	1992	3	492	Lorenzo's Oil	Michaela Odone	f
877	1992	3	495	Howards End	Margaret Schlegel	t
878	1991	3	507	Thelma & Louise	Thelma	f
879	1991	3	508	Rambling Rose	Rose	f
880	1991	3	496	The Silence of the Lambs	Clarice Starling	t
881	1991	3	509	For the Boys	Dixie Leonard	f
882	1991	3	492	Thelma & Louise	Louise	f
883	1990	3	510	Misery	Annie Wilkes	t
884	1990	3	511	The Grifters	Lilly Dillon	f
885	1990	3	482	Pretty Woman	Vivian Ward	f
886	1990	3	450	Postcards from the Edge	Suzanne Vale	f
887	1990	3	512	Mr. & Mrs. Bridge	India Bridge	f
888	1989	3	513	Camille Claudel	Camille Claudel	f
889	1989	3	514	Shirley Valentine	Shirley Valentine	f
890	1989	3	497	Music Box	Ann Talbot	f
891	1989	3	506	The Fabulous Baker Boys	Susie Diamond	f
892	1989	3	515	Driving Miss Daisy	Daisy Werthan	t
893	1988	3	516	Dangerous Liaisons	Marquise de Merteuil	f
894	1988	3	496	The Accused	Sarah Tobias	t
895	1988	3	517	Working Girl	Tess McGill	f
896	1988	3	450	A Cry in the Dark	Lindy	f
897	1988	3	518	Gorillas in the Mist	Dian Fossey	f
898	1987	3	519	Moonstruck	Loretta Castorini	t
899	1987	3	516	Fatal Attraction	Alex Forrest	f
900	1987	3	502	Broadcast News	Jane Craig	f
901	1987	3	520	Anna	Anna	f
902	1987	3	450	Ironweed	Helen	f
903	1986	3	521	The Morning After	Alex Sternbergen	f
904	1986	3	522	Children of a Lesser God	Sarah	t
905	1986	3	478	Crimes of the Heart	Babe Magrath	f
906	1986	3	523	Peggy Sue Got Married	Peggy Sue	f
907	1986	3	518	Aliens	Ripley	f
908	1985	3	524	Agnes of God	Sister Miriam Ruth	f
909	1985	3	525	The Color Purple	Celie	f
910	1985	3	497	Sweet Dreams	Patsy Cline	f
911	1985	3	526	The Trip to Bountiful	Mrs. Watts	t
912	1985	3	450	Out of Africa	Karen	f
913	1984	3	527	A Passage to India	Adela Quested	f
914	1984	3	528	Places in the Heart	Edna Spalding	t
915	1984	3	497	Country	Jewell Ivy	f
916	1984	3	529	The Bostonians	Olive Chancellor	f
917	1984	3	478	The River	Mae Garvey	f
918	1983	3	530	Testament	Carol Wetherly	f
919	1983	3	531	Terms of Endearment	Aurora Greenway	t
920	1983	3	450	Silkwood	Karen Silkwood	f
921	1983	3	532	Educating Rita	Rita	f
922	1983	3	503	Terms of Endearment	Emma Horton	f
923	1982	3	533	Victor/Victoria	Victor/Victoria	f
924	1982	3	497	Frances	Frances Farmer	f
925	1982	3	478	Missing	Beth Horman	f
926	1982	3	450	Sophie's Choice	Sophie	t
927	1982	3	503	An Officer and a Gentleman	Paula Pokrifki	f
928	1981	3	534	On Golden Pond	Ethel Thayer	t
929	1981	3	470	Reds	Louise Bryant	f
930	1981	3	535	Only When I Laugh	Georgia	f
931	1981	3	492	Atlantic City	Sally	f
932	1981	3	450	The French Lieutenant's Woman	Sara Woodruff/Anna	f
933	1980	3	481	Resurrection	Edna May	f
934	1980	3	536	Private Benjamin	Judy Benjamin	f
935	1980	3	537	Ordinary People	Beth Jarrett	f
936	1980	3	538	Gloria	Gloria Swenson	f
937	1980	3	478	Coal Miner's Daughter	Loretta Lynn	t
938	1979	3	539	Starting Over	Marilyn Homberg	f
939	1979	3	528	Norma Rae	Norma Rae	t
940	1979	3	521	The China Syndrome	Kimberly Wells	f
941	1979	3	535	Chapter Two	Jennie MacLaine	f
942	1979	3	509	The Rose	Rose	f
943	1978	3	540	Autumn Sonata	Charlotte Andergast	f
944	1978	3	481	Same Time, Next Year	Doris	f
945	1978	3	539	An Unmarried Woman	Erica	f
946	1978	3	521	Coming Home	Sally Hyde	t
947	1978	3	526	Interiors	Eve	f
948	1977	3	524	The Turning Point	Emma Jacklin	f
949	1977	3	521	Julia	Lillian Hellman	f
950	1977	3	470	Annie Hall	Annie Hall	t
951	1977	3	531	The Turning Point	Deedee Rodgers	f
952	1977	3	535	The Goodbye Girl	Paula McFadden	f
953	1976	3	541	Cousin, Cousine	Marthe	f
954	1976	3	542	Network	Diana Christensen	t
955	1976	3	543	Rocky	Adrian	f
956	1976	3	478	Carrie	Carrie White	f
957	1976	3	544	Face to Face	Dr. Jenny Isaksson	f
958	1975	3	513	The Story of Adele H.	Adele Hugo	f
959	1975	3	545	Tommy	Nora Walker Hobbs	f
960	1975	3	546	One Flew over the Cuckoo's Nest	Nurse Mildred Ratched	t
961	1975	3	547	Hedda	Hedda Gabler	f
962	1975	3	548	Hester Street	Gitl	f
963	1974	3	481	Alice Doesn't Live Here Anymore	Alice Hyatt	t
964	1974	3	549	Claudine	Claudine	f
965	1974	3	542	Chinatown	Evelyn Cross Mulwray	f
966	1974	3	550	Lenny	Honey Bruce	f
967	1974	3	538	A Woman under the Influence	Mabel Longhetti	f
968	1973	3	481	The Exorcist	Chris MacNeil	f
969	1973	3	547	A Touch of Class	Vicki Allessio	t
970	1973	3	535	Cinderella Liberty	Maggie Paul	f
971	1973	3	551	The Way We Were	Katie Morosky	f
972	1973	3	512	Summer Wishes, Winter Dreams	Rita Walden	f
973	1972	3	552	Cabaret	Sally Bowles	t
974	1972	3	553	Lady Sings the Blues	Billie Holiday	f
975	1972	3	554	Travels with My Aunt	Aunt Augusta	f
976	1972	3	555	Sounder	Rebecca Morgan	f
977	1972	3	544	The Emigrants	Kristina	f
978	1971	3	456	McCabe & Mrs. Miller	Mrs. Constance Miller	f
979	1971	3	521	Klute	Bree Daniel	t
980	1971	3	547	Sunday Bloody Sunday	Alex Greville	f
981	1971	3	529	Mary, Queen of Scots	Mary, Queen of Scots	f
982	1971	3	556	Nicholas and Alexandra	Alexandra	f
983	1970	3	530	The Great White Hope	Eleanor Bachman	f
984	1970	3	547	Women in Love	Gudrun Brangwen	t
985	1970	3	557	Love Story	Jenny Cavilleri	f
986	1970	3	558	Ryan's Daughter	Rosy Ryan	f
987	1970	3	559	Diary of a Mad Housewife	Tina Balser	f
988	1969	3	560	Anne of the Thousand Days	Anne Boleyn	f
989	1969	3	521	They Shoot Horses, Don't They?	Gloria Beatty	f
990	1969	3	552	The Sterile Cuckoo	Pookie Adams	f
991	1969	3	561	The Happy Ending	Mary Wilson	f
992	1969	3	554	The Prime of Miss Jean Brodie	Miss Jean Brodie	t
993	1968	3	534	The Lion in Winter	Queen Eleanor of Aquitaine	t
994	1968	3	562	The Subject Was Roses	Nettie Cleary	f
995	1968	3	529	Isadora	Isadora Duncan	f
996	1968	3	551	Funny Girl	Fanny Brice	t
997	1968	3	512	Rachel, Rachel	Rachel Cameron	f
998	1967	3	524	The Graduate	Mrs. Robinson	f
999	1967	3	542	Bonnie and Clyde	Bonnie Parker	f
1000	1967	3	563	The Whisperers	Mrs. Ross	f
1001	1967	3	564	Wait until Dark	Susy Hendrix	f
1002	1967	3	534	Guess Who's Coming to Dinner	Christina Drayton	t
1003	1966	3	565	A Man and a Woman	Anne Gauthier	f
1004	1966	3	566	The Shop on Main Street	Rozalie Lautmanova	f
1005	1966	3	567	Georgy Girl	Georgy Parkin	f
1006	1966	3	529	Morgan!	Leonie Delt	f
1007	1966	3	568	Who's Afraid of Virginia Woolf?	Martha	t
1008	1965	3	533	The Sound of Music	Maria	f
1009	1965	3	456	Darling	Diana Scott	t
1010	1965	3	569	The Collector	Miranda Grey	f
1011	1965	3	570	A Patch of Blue	Selina D'Arcey	f
1012	1965	3	571	Ship of Fools	La Condessa	f
1013	1964	3	533	Mary Poppins	Mary Poppins	t
1014	1964	3	524	The Pumpkin Eater	Jo Armitage	f
1015	1964	3	572	Marriage Italian Style	Filomena Marturano	f
1016	1964	3	573	The Unsinkable Molly Brown	Molly Brown	f
1017	1964	3	574	Seance on a Wet Afternoon	Myra Savage	f
1018	1963	3	575	The L-Shaped Room	Jane	f
1019	1963	3	531	Irma La Douce	Irma La Douce	f
1020	1963	3	562	Hud	Alma	t
1021	1963	3	576	This Sporting Life	Mrs. Hammond	f
1022	1963	3	577	Love with the Proper Stranger	Angie	f
1023	1962	3	524	The Miracle Worker	Annie Sullivan	t
1024	1962	3	578	What Ever Happened to Baby Jane?	Jane Hudson	f
1025	1962	3	534	Long Day's Journey into Night	Mary Tyrone	f
1026	1962	3	526	Sweet Bird of Youth	Alexandra Del Lago	f
1027	1962	3	579	Days of Wine and Roses	Kirsten Arnesen	f
1028	1961	3	564	Breakfast at Tiffany's	Holly Golightly	f
1029	1961	3	580	The Hustler	Sarah Packard	f
1030	1961	3	572	Two Women	Cesira	t
1031	1961	3	526	Summer and Smoke	Alma Winemiller	f
1032	1961	3	577	Splendor in the Grass	Wilma Dean Loomis	f
1033	1960	3	581	Sunrise at Campobello	Eleanor Roosevelt	f
1034	1960	3	582	The Sundowners	Ida Carmody	f
1035	1960	3	531	The Apartment	Fran Kubelik	f
1036	1960	3	583	Never on Sunday	Ilya	f
1037	1960	3	568	Butterfield 8	Gloria Wandrous	t
1038	1959	3	584	Pillow Talk	Jan Morrow	f
1039	1959	3	564	The Nun's Story	Sister Luke	f
1040	1959	3	534	Suddenly, Last Summer	Mrs. Venable	f
1041	1959	3	571	Room at the Top	Alice Aisgill	t
1042	1959	3	568	Suddenly, Last Summer	Catherine Holly	f
1043	1958	3	585	I Want to Live!	Barbara Graham	t
1044	1958	3	582	Separate Tables	Sibyl Railton-Bell	f
1045	1958	3	531	Some Came Running	Ginny Moorhead	f
1046	1958	3	586	Auntie Mame	Auntie Mame Dennis	f
1047	1958	3	568	Cat on a Hot Tin Roof	Maggie Pollitt	f
1048	1957	3	582	Heaven Knows, Mr. Allison	Sister Angela	f
1049	1957	3	587	Wild Is the Wind	Gioia	f
1050	1957	3	568	Raintree County	Susanna Drake	f
1051	1957	3	588	Peyton Place	Constance MacKenzie	f
1052	1957	3	512	The Three Faces of Eve	Eve White/Eve Black/Jane	t
1053	1956	3	589	Baby Doll	Baby Doll	f
1054	1956	3	540	Anastasia	The Woman	t
1055	1956	3	534	The Rainmaker	Lizzie Curry	f
1056	1956	3	590	The Bad Seed	Christine Penmark	f
1057	1956	3	582	The King and I	Anna	f
1058	1955	3	585	I'll Cry Tomorrow	Lillian Roth	f
1059	1955	3	534	Summertime	Jane Hudson	f
1060	1955	3	591	Love Is a Many-Splendored Thing	Han Suyin	f
1061	1955	3	587	The Rose Tattoo	Serafina Della Rose	t
1062	1955	3	592	Interrupted Melody	Marjorie Lawrence	f
1063	1954	3	593	Carmen Jones	Carmen Jones	f
1064	1954	3	594	A Star Is Born	Esther Blodgett/Vicki Lester	f
1065	1954	3	564	Sabrina	Sabrina Fairchild	f
1066	1954	3	595	The Country Girl	Georgie Elgin	t
1067	1954	3	596	Magnificent Obsession	Helen Phillips	f
1068	1953	3	575	Lili	Lili Daurier	f
1069	1953	3	597	Mogambo	Eloise 'Honey Bear' Kelly	f
1070	1953	3	564	Roman Holiday	Princess Anne	t
1071	1953	3	582	From Here to Eternity	Karen Holmes	f
1072	1953	3	598	The Moon Is Blue	Patty O'Neill	f
1073	1952	3	599	Come Back, Little Sheba	Lola Delaney	t
1074	1952	3	600	Sudden Fear	Myra Hudson	f
1075	1952	3	578	The Star	Margaret Elliot	f
1076	1952	3	601	The Member of the Wedding	Frankie Addams	f
1077	1952	3	585	With a Song in My Heart	Jane Froman	f
1078	1951	3	534	The African Queen	Rose Sayer	f
1079	1951	3	602	A Streetcar Named Desire	Blanche DuBois	t
1080	1951	3	592	Detective Story	Mary McLeod	f
1081	1951	3	603	A Place in the Sun	Alice Tripp	f
1082	1951	3	596	The Blue Veil	Louise Mason	f
1083	1950	3	604	All about Eve	Eve Harrington	f
1084	1950	3	578	All about Eve	Margo Channing	f
1085	1950	3	605	Born Yesterday	Billie Dawn	t
1086	1950	3	592	Caged	Marie Allen	f
1087	1950	3	606	Sunset Blvd.	Norma Desmond	f
1088	1949	3	607	Pinky	Pinky	f
1089	1949	3	608	The Heiress	Catherine Sloper	t
1090	1949	3	585	My Foolish Heart	Eloise Winters	f
1091	1949	3	582	Edward, My Son	Evelyn Boult	f
1092	1949	3	609	Come to the Stable	Sister Margaret	f
1093	1948	3	540	Joan of Arc	Joan of Arc	f
1094	1948	3	608	The Snake Pit	Virginia Stuart Cunningham	f
1095	1948	3	610	I Remember Mama	Mama	f
1096	1948	3	611	Sorry, Wrong Number	Leona Stevenson	f
1097	1948	3	596	Johnny Belinda	Belinda McDonald	t
1098	1947	3	600	Possessed	Louise Howell	f
1099	1947	3	585	Smash-Up--The Story of a Woman	Angie	f
1100	1947	3	612	Gentleman's Agreement	Kathy	f
1101	1947	3	586	Mourning Becomes Electra	Lavinia Mannon	f
1102	1947	3	609	The Farmer's Daughter	Katrin Holstrom	t
1103	1946	3	608	To Each His Own	Jody Norris	t
1104	1946	3	613	Brief Encounter	Laura Jesson	f
1105	1946	3	591	Duel in the Sun	Pearl Chavez	f
1106	1946	3	586	Sister Kenny	Elizabeth Kenny	f
1107	1946	3	596	The Yearling	Ma Baxter	f
1108	1945	3	540	The Bells of St. Mary's	Sister Benedict	f
1109	1945	3	600	Mildred Pierce	Mildred Pierce	t
1110	1945	3	581	The Valley of Decision	Mary Rafferty	f
1111	1945	3	591	Love Letters	Singleton	f
1112	1945	3	614	Leave Her to Heaven	Ellen Berent	f
1113	1944	3	540	Gaslight	Paula Alquist	t
1114	1944	3	615	Since You Went Away	Anne Hilton	f
1115	1944	3	578	Mr. Skeffington	Fanny Trellis Skeffington	f
1116	1944	3	581	Mrs. Parkington	Susie Parkington	f
1117	1944	3	611	Double Indemnity	Phyllis Dietrichson	f
1118	1943	3	616	The More the Merrier	Connie Milligan	f
1119	1943	3	540	For Whom the Bell Tolls	Maria	f
1120	1943	3	617	The Constant Nymph	Teresa 'Tessa' Sanger	f
1121	1943	3	581	Madame Curie	Madame Marie Curie	f
1122	1943	3	591	The Song of Bernadette	Bernadette Soubirous	t
1123	1942	3	578	Now, Voyager	Charlotte Vale	f
1124	1942	3	581	Mrs. Miniver	Kay Miniver	t
1125	1942	3	534	Woman of the Year	Tess Harding	f
1126	1942	3	586	My Sister Eileen	Ruth Sherwood	f
1127	1942	3	618	The Pride of the Yankees	Eleanor Gehrig	f
1128	1941	3	578	The Little Foxes	Regina Hubbard Giddens	f
1129	1941	3	608	Hold Back the Dawn	Emmy Brown	f
1130	1941	3	617	Suspicion	Lina McLaidlaw	t
1131	1941	3	581	Blossoms in the Dust	Edna Gladney	f
1132	1941	3	611	Ball of Fire	Sugarpuss O'Shea	f
1133	1940	3	578	The Letter	Leslie Crosbie	f
1134	1940	3	617	Rebecca	Mrs. de Winter	f
1135	1940	3	534	The Philadelphia Story	Tracy Lord	f
1136	1940	3	619	Kitty Foyle	Kitty Foyle	t
1137	1940	3	620	Our Town	Emily Webb	f
1138	1939	3	578	Dark Victory	Judith Traherne	f
1139	1939	3	610	Love Affair	Terry McKay	f
1140	1939	3	621	Ninotchka	Lena Yakushova (Ninotchka)	f
1141	1939	3	581	Goodbye, Mr. Chips	Katherine Chipping	f
1142	1939	3	602	Gone with the Wind	Scarlett O'Hara	t
1143	1938	3	622	White Banners	Hannah	f
1144	1938	3	578	Jezebel	Julie Morrison	t
1145	1938	3	623	Pygmalion	Eliza Doolittle	f
1146	1938	3	624	Marie Antoinette	Marie Antoinette	f
1147	1938	3	625	Three Comrades	Pat Hollmann	f
1148	1937	3	610	The Awful Truth	Lucy Warriner	f
1149	1937	3	621	Camille	Marguerite Gautier (Camille)	f
1150	1937	3	626	A Star Is Born	Esther Blodgett/Vicki Lester	f
1151	1937	3	627	The Good Earth	O-Lan	t
1152	1937	3	611	Stella Dallas	Stella Dallas	f
1153	1936	3	610	Theodora Goes Wild	Theodora Lynn	f
1154	1936	3	628	Valiant Is the Word for Carrie	Carrie Snyder	f
1155	1936	3	629	My Man Godfrey	Irene Bullock	f
1156	1936	3	627	The Great Ziegfeld	Anna Held	t
1157	1936	3	624	Romeo and Juliet	Juliet	f
1158	1935	3	630	Escape Me Never	Gemma Jones	f
1159	1935	3	615	Private Worlds	Jane Everest	f
1160	1935	3	578	Dangerous	Joyce Heath	t
1161	1935	3	534	Alice Adams	Alice Adams	f
1162	1935	3	631	Becky Sharp	Becky Sharp	f
1163	1935	3	632	The Dark Angel	Kitty Vane	f
1164	1934	3	615	It Happened One Night	Ellie Andrews	t
1165	1934	3	578	Of Human Bondage	Mildred	f
1166	1934	3	633	One Night of Love	Mary	f
1167	1934	3	624	The Barretts of Wimpole Street	Elizabeth Barrett	f
1168	1932	3	534	Morning Glory	Eva Lovelace	t
1169	1932	3	634	Lady for a Day	Apple Annie	f
1170	1932	3	635	Cavalcade	Jane Marryot	f
1171	1931	3	636	Emma	Emma	f
1172	1931	3	637	The Guardsman	The Actress	f
1173	1931	3	638	The Sin of Madelon Claudet	Madelon	t
1174	1930	3	639	Morocco	Amy Jolly	f
1175	1930	3	636	Min and Bill	Min	t
1176	1930	3	610	Cimarron	Sabra Cravat	f
1177	1930	3	640	Holiday	Linda Seton	f
1178	1930	3	624	A Free Soul	Jan Ashe	f
1179	1929	3	641	The Devil's Holiday	Hallie Hobart	f
1180	1929	3	642	Sarah and Son	Sarah Storm	f
1181	1929	3	621	Anna Christie	Anna Christie	f
1182	1929	3	624	The Divorcee	Jerry	t
1183	1929	3	624	Their Own Desire	Lucia 'Lally' Marlett	f
1184	1929	3	606	The Trespasser	Marion Donnell	f
1185	1928	3	642	Madame X	Jacqueline Floriot	f
1186	1928	3	643	The Barker	Carrie	f
1187	1928	3	644	The Letter	Leslie Crosbie	f
1188	1928	3	645	The Divine Lady	Emma Hart, Lady Hamilton	f
1189	1928	3	646	The Broadway Melody	Hank Mahoney	f
1190	1928	3	647	Coquette	Norma Besant	t
1191	1927	3	648	A Ship Comes In	Mrs. Pleznik	f
1192	1927	3	626	7th Heaven	Diane	t
1193	1927	3	606	Sadie Thompson	Sadie Thompson	f
1194	2010	4	649	The Fighter	Charlene Fleming	f
1195	2010	4	487	The King's Speech	Queen Elizabeth	f
1196	2010	4	453	The Fighter	Alice Ward	t
1197	2010	4	650	True Grit	Mattie Ross	f
1198	2010	4	651	Animal Kingdom	Janine 'Smurf' Cody	f
1199	2009	4	460	Nine	Carla	f
1200	2009	4	652	Up in the Air	Alex Goran	f
1201	2009	4	653	Crazy Heart	Jean Craddock	f
1202	2009	4	654	Up in the Air	Natalie Keener	f
1203	2009	4	655	Precious: Based on the Novel 'Push' by Sapphire	Mary	t
1204	2008	4	649	Doubt	Sister James	f
1205	2008	4	460	Vicky Cristina Barcelona	Maria Elena	t
1206	2008	4	656	Doubt	Mrs. Miller	f
1207	2008	4	657	The Curious Case of Benjamin Button	Queenie	f
1208	2008	4	658	The Wrestler	Cassidy	f
1209	2007	4	455	I'm Not There	Jude	f
1210	2007	4	659	American Gangster	Mama Lucas	f
1211	2007	4	660	Atonement	Briony Tallis, aged 13	f
1212	2007	4	661	Gone Baby Gone	Helene McCready	f
1213	2007	4	662	Michael Clayton	Karen Crowder	t
1214	2006	4	663	Babel	Amelia	f
1215	2006	4	455	Notes on a Scandal	Sheba Hart	f
1216	2006	4	664	Little Miss Sunshine	Olive	f
1217	2006	4	665	Dreamgirls	Effie White	t
1218	2006	4	666	Babel	Chieko	f
1219	2005	4	649	Junebug	Ashley	f
1220	2005	4	667	Capote	Nelle Harper Lee	f
1221	2005	4	490	North Country	Glory	f
1222	2005	4	668	The Constant Gardener	Tessa Quayle	t
1223	2005	4	445	Brokeback Mountain	Alma	f
1224	2004	4	455	The Aviator	Katharine Hepburn	t
1225	2004	4	458	Kinsey	Clara McMillen	f
1226	2004	4	669	Sideways	Maya	f
1227	2004	4	670	Hotel Rwanda	Tatiana Rusesabagina	f
1228	2004	4	444	Closer	Alice	f
1229	2003	4	671	House of Sand and Fog	Nadi	f
1230	2003	4	672	Pieces of April	Joy Burns	f
1231	2003	4	673	Mystic River	Celeste Boyle	f
1232	2003	4	502	Thirteen	Melanie	f
1233	2003	4	476	Cold Mountain	Ruby Thewes	t
1234	2002	4	510	About Schmidt	Roberta Hertzel	f
1235	2002	4	475	The Hours	Laura Brown	f
1236	2002	4	674	Chicago	Matron Mama Morton	f
1237	2002	4	450	Adaptation	Susan Orlean	f
1238	2002	4	675	Chicago	Velma Kelly	t
1239	2001	4	676	A Beautiful Mind	Alicia Nash	t
1240	2001	4	447	Gosford Park	Mrs. Wilson	f
1241	2001	4	554	Gosford Park	Constance, Countess of Trentham	f
1242	2001	4	658	In the Bedroom	Natalie Strout	f
1243	2001	4	454	Iris	Young Iris Murdoch	f
1244	2000	4	461	Chocolat	Armande Voizin	f
1245	2000	4	673	Pollock	Lee Krasner	t
1246	2000	4	677	Almost Famous	Penny Lane	f
1247	2000	4	490	Almost Famous	Elaine Miller	f
1248	2000	4	532	Billy Elliot	Mrs. Wilkinson	f
1249	1999	4	678	The Sixth Sense	Lynn Sear	f
1250	1999	4	452	Girl, Interrupted	Lisa Rowe	t
1251	1999	4	667	Being John Malkovich	Maxine	f
1252	1999	4	471	Sweet and Lowdown	Hattie	f
1253	1999	4	679	Boys Don't Cry	Lana Tisdel	f
1254	1998	4	510	Primary Colors	Libby	f
1255	1998	4	489	Little Voice	Mari	f
1256	1998	4	461	Shakespeare in Love	Queen Elizabeth I	t
1257	1998	4	680	Hilary and Jackie	Hilary du Pré	f
1258	1998	4	567	Gods and Monsters	Hanna	f
1259	1997	4	681	L.A. Confidential	Lynn Bracken	t
1260	1997	4	682	In & Out	Emily Montgomery	f
1261	1997	4	683	Good Will Hunting	Skylar	f
1262	1997	4	475	Boogie Nights	Amber Waves	f
1263	1997	4	684	Titanic	Old Rose	f
1264	1996	4	479	The Crucible	Elizabeth Proctor	f
1265	1996	4	685	The Mirror Has Two Faces	Hannah Morgan	f
1266	1996	4	480	The English Patient	Hana	t
1267	1996	4	686	The Portrait of a Lady	Madame Serena Merle	f
1268	1996	4	687	Secrets & Lies	Hortense	f
1269	1995	4	479	Nixon	Pat Nixon	f
1270	1995	4	688	Apollo 13	Marilyn Lovell	f
1271	1995	4	689	Mighty Aphrodite	Linda	t
1272	1995	4	690	Georgia	Georgia	f
1273	1995	4	454	Sense and Sensibility	Marianne Dashwood	f
1274	1994	4	691	Tom & Viv	Rose Haigh-Wood	f
1275	1994	4	447	The Madness of King George	Queen Charlotte	f
1276	1994	4	692	Pulp Fiction	Mia	f
1277	1994	4	693	Bullets over Broadway	Olive Neal	f
1278	1994	4	694	Bullets over Broadway	Helen Sinclair	t
1279	1993	4	502	The Firm	Tammy Hemphill	f
1280	1993	4	695	The Piano	Flora	t
1281	1993	4	696	Fearless	Carla Rodrigo	f
1282	1993	4	499	The Age of Innocence	May Welland	f
1283	1993	4	495	In the Name of the Father	Gareth Peirce	f
1284	1992	4	527	Husbands and Wives	Sally	f
1285	1992	4	697	Enchanted April	Mrs. Fisher	f
1286	1992	4	529	Howards End	Ruth Wilcox	f
1287	1992	4	498	Damage	Ingrid	f
1288	1992	4	658	My Cousin Vinny	Mona Lisa Vito	t
1289	1991	4	698	Rambling Rose	Mother	f
1290	1991	4	699	Cape Fear	Danielle Bowden	f
1291	1991	4	700	The Prince of Tides	Lila Wingo Newbury	f
1292	1991	4	701	The Fisher King	Anne	t
1293	1991	4	515	Fried Green Tomatoes	Ninny Threadgoode	f
1294	1990	4	441	The Grifters	Myra Langtry	f
1295	1990	4	702	Good Fellas	Karen Hill	f
1296	1990	4	525	Ghost	Oda Mae Brown	t
1297	1990	4	698	Wild at Heart	Marietta Fortune	f
1298	1990	4	505	Dances With Wolves	Stands With A Fist	f
1299	1989	4	703	My Left Foot	Mrs. Brown	t
1300	1989	4	511	Enemies, A Love Story	Tamara	f
1301	1989	4	704	Enemies, A Love Story	Masha	f
1302	1989	4	482	Steel Magnolias	Shelby Eatenton	f
1303	1989	4	694	Parenthood	Helen	f
1304	1988	4	682	Working Girl	Cyn	f
1305	1988	4	507	The Accidental Tourist	Muriel	t
1306	1988	4	490	Mississippi Burning	Mrs. Pell	f
1307	1988	4	506	Dangerous Liaisons	Madame de Tourvel	f
1308	1988	4	518	Working Girl	Katharine Parker	f
1309	1987	4	705	Gaby - A True Story	Florencia	f
1310	1987	4	706	Fatal Attraction	Beth Gallagher	f
1311	1987	4	707	Moonstruck	Rose Castorini	t
1312	1987	4	708	Throw Momma from the Train	Momma	f
1313	1987	4	709	The Whales of August	Tisha Doughty	f
1314	1986	4	710	Crimes of the Heart	Chick Boyle	f
1315	1986	4	580	Children of a Lesser God	Mrs. Norman	f
1316	1986	4	711	The Color of Money	Carmen	f
1317	1986	4	554	A Room with a View	Charlotte Bartlett	f
1318	1986	4	694	Hannah and Her Sisters	Holly	t
1319	1985	4	712	The Color Purple	Shug Avery	f
1320	1985	4	511	Prizzi's Honor	Maerose Prizzi	t
1321	1985	4	713	Twice in a Lifetime	Sunny Sobel	f
1322	1985	4	714	Agnes of God	Sister Agnes	f
1323	1985	4	715	The Color Purple	Sofia	f
1324	1984	4	716	A Passage to India	Mrs. Moore	t
1325	1984	4	516	The Natural	Iris	f
1326	1984	4	717	Places in the Heart	Margaret Lomax	f
1327	1984	4	718	Swing Shift	Hazel Zanussi	f
1328	1984	4	526	The Pope of Greenwich Village	Mrs. Ritter	f
1329	1983	4	519	Silkwood	Dolly Pelliker	f
1330	1983	4	516	The Big Chill	Sarah	f
1331	1983	4	719	The Year of Living Dangerously	Billy Kwan	t
1332	1983	4	720	Yentl	Hadaas	f
1333	1983	4	721	Cross Creek	Geechee	f
1334	1982	4	516	The World According to Garp	Jenny Fields	f
1335	1982	4	722	Tootsie	Sandy Lester	f
1336	1982	4	497	Tootsie	Julie Nichols	t
1337	1982	4	574	Frances	Lillian Farmer	f
1338	1982	4	723	Victor/Victoria	Norma	f
1339	1981	4	724	Absence of Malice	Teresa	f
1340	1981	4	521	On Golden Pond	Chelsea Thayer Wayne	f
1341	1981	4	725	Only When I Laugh	Toby Landau	f
1342	1981	4	726	Ragtime	Evelyn Nesbit	f
1343	1981	4	727	Reds	Emma Goldman	t
1344	1980	4	728	Private Benjamin	Captain Doreen Lewis	f
1345	1980	4	729	Resurrection	Grandma Pearl	f
1346	1980	4	730	Raging Bull	Vicki LaMotta	f
1347	1980	4	731	Inside Moves	Louise	f
1348	1980	4	732	Melvin and Howard	Lynda Dummar	t
1349	1979	4	530	Kramer vs. Kramer	Margaret Phelps	f
1350	1979	4	733	Breaking Away	Mrs. Stohler	f
1351	1979	4	734	Starting Over	Jessica Potter	f
1352	1979	4	735	Manhattan	Tracy	f
1353	1979	4	450	Kramer vs. Kramer	Joanna Kramer	t
1354	1978	4	736	Heaven Can Wait	Julia Farnsworth	f
1355	1978	4	737	Coming Home	Viola Munson	f
1356	1978	4	554	California Suite	Diana Barrie	t
1357	1978	4	727	Interiors	Pearl	f
1358	1978	4	450	The Deer Hunter	Linda	f
1359	1977	4	738	The Turning Point	Emilia Rodgers	f
1360	1977	4	739	The Goodbye Girl	Lucy McFadden	f
1361	1977	4	724	Close Encounters of the Third Kind	Jillian Guiler	f
1362	1977	4	529	Julia	Julia	t
1363	1977	4	740	Looking for Mr. Goodbar	Katherine Dunn	f
1364	1976	4	530	All the President's Men	The Bookkeeper	f
1365	1976	4	496	Taxi Driver	Iris	f
1366	1976	4	741	Voyage of the Damned	Lilian Rosen	f
1367	1976	4	580	Carrie	Margaret White	f
1368	1976	4	742	Network	Louise Schumacher	t
1369	1975	4	743	Nashville	Barbara Jean	f
1370	1975	4	741	Shampoo	Felicia Karpf	t
1371	1975	4	744	Farewell, My Lovely	Mrs. Florian	f
1372	1975	4	745	Nashville	Linnea Reese	f
1373	1975	4	746	Jacqueline Susann's Once Is Not Enough	Linda	f
1374	1974	4	540	Murder on the Orient Express	Greta Ohlsson	t
1375	1974	4	747	Day for Night	Severine	f
1376	1974	4	748	Blazing Saddles	Lili Von Shtupp	f
1377	1974	4	698	Alice Doesn't Live Here Anymore	Flo	f
1378	1974	4	543	The Godfather Part II	Connie Corleone	f
1379	1973	4	749	The Exorcist	Regan MacNeil	f
1380	1973	4	750	American Graffiti	Debbie	f
1381	1973	4	748	Paper Moon	Trixie Delight	f
1382	1973	4	751	Paper Moon	Addie Loggins	t
1383	1973	4	752	Summer Wishes, Winter Dreams	Mrs. Pritchett	f
1384	1972	4	753	The Heartbreak Kid	Lila	f
1385	1972	4	754	Butterflies Are Free	Mrs. Baker	t
1386	1972	4	526	Pete 'n' Tillie	Gertrude	f
1387	1972	4	755	Fat City	Oma	f
1388	1972	4	603	The Poseidon Adventure	Belle Rosen	f
1389	1971	4	545	Carnal Knowledge	Bobbie	f
1390	1971	4	481	The Last Picture Show	Lois Farrow	f
1391	1971	4	756	Who Is Harry Kellerman and Why Is He Saying Those Terrible Things about Me?	Allison	f
1392	1971	4	757	The Last Picture Show	Ruth Popper	t
1393	1971	4	758	The Go-Between	Mrs. Maudsley	f
1394	1970	4	759	Five Easy Pieces	Rayette Dipesto	f
1395	1970	4	741	The Landlord	Mrs. Enders	f
1396	1970	4	638	Airport	Ada Quonsett	t
1397	1970	4	760	M*A*S*H	Major Margaret 'Hot Lips' Houlihan	f
1398	1970	4	727	Airport	Inez Guerrero	f
1399	1969	4	761	Last Summer	Rhoda	f
1400	1969	4	736	Bob & Carol & Ted & Alice	Alice Henderson	f
1401	1969	4	536	Cactus Flower	Toni Simmons	t
1402	1969	4	744	Midnight Cowboy	Cass	f
1403	1969	4	762	They Shoot Horses, Don't They?	Alice	f
1404	1968	4	763	Faces	Maria Forst	f
1405	1968	4	764	Rosemary's Baby	Minnie Castevet	t
1406	1968	4	765	The Heart Is a Lonely Hunter	Mick Kelly	f
1407	1968	4	766	Funny Girl	Rose Brice	f
1408	1968	4	767	Rachel, Rachel	Calla Mackie	f
1409	1967	4	768	Thoroughly Modern Millie	Muzzy Van Hossmere	f
1410	1967	4	769	Barefoot in the Park	Mrs. Ethel Banks	f
1411	1967	4	767	Bonnie and Clyde	Blanche Barrow	t
1412	1967	4	770	Guess Who's Coming to Dinner	Mrs. Prentice	f
1413	1967	4	771	The Graduate	Elaine Robinson	f
1414	1966	4	772	Who's Afraid of Virginia Woolf?	Honey	t
1415	1966	4	623	A Man for All Seasons	Alice More	f
1416	1966	4	773	Hawaii	Malama	f
1417	1966	4	774	Alfie	Lily	f
1418	1966	4	526	You're a Big Boy Now	Margary Chanticleer	f
1419	1965	4	764	Inside Daisy Clover	The Dealer	f
1420	1965	4	775	Othello	Emilia	f
1421	1965	4	554	Othello	Desdemona	f
1422	1965	4	603	A Patch of Blue	Rose-Ann D'Arcey	t
1423	1965	4	776	The Sound of Music	Mother Abbess	f
1424	1964	4	777	My Fair Lady	Mrs. Higgins	f
1425	1964	4	563	The Chalk Garden	Mrs. St. Maugham	f
1426	1964	4	778	The Night of the Iguana	Judith Fellowes	f
1427	1964	4	779	Zorba the Greek	Madame Hortense	t
1428	1964	4	780	Hush...Hush, Sweet Charlotte	Velma Cruther	f
1429	1963	4	781	Tom Jones	Molly	f
1430	1963	4	563	Tom Jones	Miss Western	f
1431	1963	4	775	Tom Jones	Mrs. Waters	f
1432	1963	4	782	The V.I.P.s	Duchess of Brighton	t
1433	1963	4	783	Lilies of the Field	Mother Maria	f
1434	1962	4	784	To Kill a Mockingbird	Scout Finch	f
1435	1962	4	785	The Miracle Worker	Helen Keller	t
1436	1962	4	786	Sweet Bird of Youth	Heavenly Finley	f
1437	1962	4	787	The Manchurian Candidate	Raymond's Mother	f
1438	1962	4	788	Birdman of Alcatraz	Elizabeth Stroud	f
1439	1961	4	622	The Children's Hour	Mrs. Amelia Tilford	f
1440	1961	4	594	Judgment at Nuremberg	Irene Hoffman	f
1441	1961	4	789	The Roman Spring of Mrs. Stone	Countess Magda Terribili-Gonzales	f
1442	1961	4	790	Summer and Smoke	Mrs. Winemiller	f
1443	1961	4	791	West Side Story	Anita	t
1444	1960	4	792	The Sundowners	Mrs. Firth	f
1445	1960	4	793	Elmer Gantry	Lulu Bains	t
1446	1960	4	786	The Dark at the Top of the Stairs	Reenie	f
1447	1960	4	794	Psycho	Marion Crane	f
1448	1960	4	795	Sons and Lovers	Clara Dawes	f
1449	1959	4	796	Room at the Top	Elspeth	f
1450	1959	4	797	Imitation of Life	Sarah Jane (age 18)	f
1451	1959	4	798	Imitation of Life	Annie Johnson	f
1452	1959	4	788	Pillow Talk	Alma	f
1453	1959	4	603	The Diary of Anne Frank	Mrs. Van Daan	t
1454	1958	4	799	Auntie Mame	Agnes Gooch	f
1455	1958	4	623	Separate Tables	Pat Cooper	t
1456	1958	4	800	Some Came Running	Gwen French	f
1457	1958	4	727	Lonelyhearts	Fay Doyle	f
1458	1958	4	801	The Defiant Ones	The Woman	f
1459	1957	4	802	The Bachelor Party	The Existentialist	f
1460	1957	4	803	Witness for the Prosecution	Miss Plimsoll	f
1461	1957	4	804	Peyton Place	Selena Cross	f
1462	1957	4	805	Sayonara	Katsumi	t
1463	1957	4	806	Peyton Place	Allison MacKenzie	f
1464	1956	4	807	Baby Doll	Aunt Rose Comfort	f
1465	1956	4	754	The Bad Seed	Mrs. Daigle	f
1466	1956	4	808	Giant	Luz Benedict	f
1467	1956	4	809	The Bad Seed	Rhoda Penmark	f
1468	1956	4	810	Written on the Wind	Marylee Hadley	t
1469	1955	4	811	Marty	Clara Snyder	f
1470	1955	4	812	Pete Kelly's Blues	Rose Hopkins	f
1471	1955	4	813	The Rose Tattoo	Rosa Della Rose	f
1472	1955	4	814	East of Eden	Kate	t
1473	1955	4	577	Rebel without a Cause	Judy	f
1474	1954	4	815	Executive Suite	Erica Martin	f
1475	1954	4	816	Broken Lance	Senora Devereaux	f
1476	1954	4	817	On the Waterfront	Edie Doyle	t
1477	1954	4	818	The High and the Mighty	Sally McKee	f
1478	1954	4	819	The High and the Mighty	Mary Holst	f
1479	1953	4	595	Mogambo	Linda Nordley	f
1480	1953	4	526	Hondo	Angie Lowe	f
1481	1953	4	820	Torch Song	Mrs. Stewart	f
1482	1953	4	821	From Here to Eternity	Lorene/Alma	t
1483	1953	4	788	Pickup on South Street	Moe	f
1484	1952	4	822	The Bad and the Beautiful	Rosemary Bartlow	t
1485	1952	4	823	Singin' in the Rain	Lina Lamont	f
1486	1952	4	824	Moulin Rouge	Marie Charlet	f
1487	1952	4	825	Come Back, Little Sheba	Marie Loring	f
1488	1952	4	788	With a Song in My Heart	Clancy	f
1489	1951	4	826	The Blue Veil	Annie Rawlins	f
1490	1951	4	807	Death of a Salesman	Linda Loman	f
1491	1951	4	741	Detective Story	A Shoplifter	f
1492	1951	4	827	A Streetcar Named Desire	Stella Kowalski	t
1493	1951	4	788	The Mating Season	Ellen McNulty	f
1494	1950	4	828	Caged	Evelyn Harper	f
1495	1950	4	829	All about Eve	Karen Richards	f
1496	1950	4	830	Harvey	Veta Louise Simmons	t
1497	1950	4	831	Sunset Blvd.	Betty Schaefer	f
1498	1950	4	788	All about Eve	Birdie	f
1499	1949	4	832	Pinky	Miss Em	f
1500	1949	4	829	Come to the Stable	Sister Scholastica	f
1501	1949	4	803	Come to the Stable	Miss Potts	f
1502	1949	4	808	All the King's Men	Sadie Burke	t
1503	1949	4	833	Pinky	Granny	f
1504	1948	4	834	I Remember Mama	Katrin	f
1505	1948	4	835	I Remember Mama	Aunt Trina	f
1506	1948	4	780	Johnny Belinda	Aggie McDonald	f
1507	1948	4	561	Hamlet	Ophelia	f
1508	1948	4	819	Key Largo	Gaye	t
1509	1947	4	832	The Paradine Case	Lady Sophie Horfield	f
1510	1947	4	822	Crossfire	Ginny Tremaine	f
1511	1947	4	829	Gentleman's Agreement	Anne	t
1512	1947	4	836	The Egg and I	Ma Kettle	f
1513	1947	4	837	Gentleman's Agreement	Mrs. Green	f
1514	1946	4	832	The Spiral Staircase	Mrs. Warren	f
1515	1946	4	604	The Razor's Edge	Sophie MacDonald	t
1516	1946	4	838	Duel in the Sun	Belle McCanles	f
1517	1946	4	839	Saratoga Trunk	Angelique Buiton	f
1518	1946	4	840	Anna and the King of Siam	Lady Thiang	f
1519	1945	4	841	Mildred Pierce	Ida	f
1520	1945	4	842	Mildred Pierce	Veda Pierce	f
1521	1945	4	787	The Picture of Dorian Gray	Sibyl Vane	f
1522	1945	4	843	The Corn Is Green	Bessie Watty	f
1523	1945	4	837	National Velvet	Mrs. Brown	t
1524	1944	4	832	None but the Lonely Heart	Ma Mott	t
1525	1944	4	591	Since You Went Away	Jane Hilton	f
1526	1944	4	787	Gaslight	Nancy Oliver	f
1527	1944	4	844	Dragon Seed	Ling's Wife	f
1528	1944	4	780	Mrs. Parkington	Aspacia Conti	f
1529	1943	4	777	The Song of Bernadette	Sister Vauzous	f
1530	1943	4	845	So Proudly We Hail!	Lieutenant Joan O'Doul	f
1531	1943	4	846	For Whom the Bell Tolls	Pilar	t
1532	1943	4	837	The Song of Bernadette	Louise Soubirous	f
1533	1943	4	847	Watch on the Rhine	Fanny Farrelly	f
1534	1942	4	777	Now, Voyager	Mrs. Vale	f
1535	1942	4	780	The Magnificent Ambersons	Fanny Minafer	f
1536	1942	4	848	Random Harvest	Kitty	f
1537	1942	4	849	Mrs. Miniver	Lady Beldon	f
1538	1942	4	618	Mrs. Miniver	Carol Beldon	t
1539	1941	4	850	How Green Was My Valley	Mrs. Morgan	f
1540	1941	4	851	The Great Lie	Sandra Kovak	t
1541	1941	4	852	The Little Foxes	Birdie Hubbard	f
1542	1941	4	618	The Little Foxes	Alexandra Giddens	f
1543	1941	4	853	Sergeant York	Mother York	f
1544	1940	4	854	Rebecca	Mrs. Danvers	f
1545	1940	4	855	The Grapes of Wrath	Ma Joad	t
1546	1940	4	856	The Philadelphia Story	Liz Imbrie	f
1547	1940	4	857	All This, and Heaven Too	Duchesse de Praslin	f
1548	1940	4	820	Primrose Path	Mamie Adams	f
1549	1939	4	608	Gone with the Wind	Melanie Hamilton	f
1550	1939	4	858	Wuthering Heights	Isabella	f
1551	1939	4	859	Gone with the Wind	Mammy	t
1552	1939	4	860	Drums along the Mohawk	Sarah McKlennar	f
1553	1939	4	861	Love Affair	Grandmother, Mme. Marnay	f
1554	1938	4	622	Jezebel	Aunt Belle Massey	t
1555	1938	4	862	Of Human Hearts	Mary Wilkins	f
1556	1938	4	863	Merrily We Live	Mrs. Emily Kilbourne	f
1557	1938	4	864	You Can't Take It with You	Penny Sycamore	f
1558	1938	4	865	The Great Waltz	Carla Donner	f
1559	1937	4	866	In Old Chicago	Molly O'Leary	t
1560	1937	4	867	Stage Door	Kaye Hamilton	f
1561	1937	4	868	Stella Dallas	Laurel Dallas	f
1562	1937	4	819	Dead End	Francie	f
1563	1937	4	849	Night Must Fall	Mrs. Bramson	f
1564	1936	4	862	The Gorgeous Hussy	Rachel Jackson	f
1565	1936	4	866	My Man Godfrey	Angelica Bullock	f
1566	1936	4	869	These Three	Mary Tilford	f
1567	1936	4	861	Dodsworth	Baroness von Obersdorf	f
1568	1936	4	840	Anthony Adverse	Faith Paleologue	t
\.


--
-- Name: nominations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('nominations_id_seq', 1568, true);


--
-- Data for Name: people; Type: TABLE DATA; Schema: public; Owner: -
--

COPY people (id, first_name, last_name) FROM stdin;
1	Javier	Bardem
2	Jeff	Bridges
3	Jesse	Eisenberg
4	Colin	Firth
5	James	Franco
6	George	Clooney
7	Morgan	Freeman
8	Jeremy	Renner
9	Richard	Jenkins
10	Frank	Langella
11	Sean	Penn
12	Brad	Pitt
13	Mickey	Rourke
14	Daniel	Day-Lewis
15	Johnny	Depp
16	Tommy	Lee Jones
17	Viggo	Mortensen
18	Leonardo	DiCaprio
19	Ryan	Gosling
20	Peter	O'Toole
21	Will	Smith
22	Forest	Whitaker
23	Philip	Seymour Hoffman
24	Terrence	Howard
25	Heath	Ledger
26	Joaquin	Phoenix
27	David	Strathairn
28	Don	Cheadle
29	Clint	Eastwood
30	Jamie	Foxx
31	Ben	Kingsley
32	Jude	Law
33	Bill	Murray
34	Adrien	Brody
35	Nicolas	Cage
36	Michael	Caine
37	Jack	Nicholson
38	Russell	Crowe
39	Denzel	Washington
40	Tom	Wilkinson
41	Tom	Hanks
42	Ed	Harris
43	Geoffrey	Rush
44	Richard	Farnsworth
45	Kevin	Spacey
46	Roberto	Benigni
47	Ian	McKellen
48	Nick	Nolte
49	Edward	Norton
50	Matt	Damon
51	Robert	Duvall
52	Peter	Fonda
53	Dustin	Hoffman
54	Tom	Cruise
55	Ralph	Fiennes
56	Woody	Harrelson
57	Billy	Bob Thornton
58	Richard	Dreyfuss
59	Anthony	Hopkins
60	Massimo	Troisi
61	Nigel	Hawthorne
62	Paul	Newman
63	John	Travolta
64	Laurence	Fishburne
65	Liam	Neeson
66	Robert	Downey, Jr.
67	Al	Pacino
68	Stephen	Rea
69	Warren	Beatty
70	Robert	De Niro
71	Robin	Williams
72	Kevin	Costner
73	Gerard	Depardieu
74	Richard	Harris
75	Jeremy	Irons
76	Kenneth	Branagh
77	Daniel	Day Lewis
78	Gene	Hackman
79	Edward	James Olmos
80	Max	Von Sydow
81	Michael	Douglas
82	William	Hurt
83	Marcello	Mastroianni
84	Dexter	Gordon
85	Bob	Hoskins
86	James	Woods
87	Harrison	Ford
88	James	Garner
89	Jon	Voight
90	F.	Murray Abraham
91	Albert	Finney
92	Tom	Hulce
93	Sam	Waterston
94	Tom	Conti
95	Tom	Courtenay
96	Jack	Lemmon
97	Henry	Fonda
98	Burt	Lancaster
99	Dudley	Moore
100	John	Hurt
101	Roy	Scheider
102	Peter	Sellers
103	Gary	Busey
104	Laurence	Olivier
105	Woody	Allen
106	Richard	Burton
107	Peter	Finch
108	Giancarlo	Giannini
109	William	Holden
110	Sylvester	Stallone
111	Walter	Matthau
112	Maximilian	Schell
113	James	Whitmore
114	Art	Carney
115	Marlon	Brando
116	Robert	Redford
117	Paul	Winfield
118	George	C. Scott
119	Topol	
120	Melvyn	Douglas
121	James	Earl Jones
122	Ryan	O'Neal
123	John	Wayne
124	Alan	Arkin
125	Alan	Bates
126	Ron	Moody
127	Cliff	Robertson
128	Rod	Steiger
129	Spencer	Tracy
130	Steve	McQueen
131	Paul	Scofield
132	Lee	Marvin
133	Oskar	Werner
134	Rex	Harrison
135	Anthony	Quinn
136	Sidney	Poitier
137	Gregory	Peck
138	Charles	Boyer
139	Stuart	Whitman
140	Trevor	Howard
141	Laurence	Harvey
142	Charlton	Heston
143	Paul	Muni
144	James	Stewart
145	Tony	Curtis
146	David	Niven
147	Anthony	Franciosa
148	Alec	Guinness
149	Charles	Laughton
150	Yul	Brynner
151	James	Dean
152	Kirk	Douglas
153	Rock	Hudson
154	Sir	Laurence Olivier
155	Ernest	Borgnine
156	James	Cagney
157	Frank	Sinatra
158	Humphrey	Bogart
159	Bing	Crosby
160	James	Mason
161	Dan	O'Herlihy
162	Montgomery	Clift
163	Gary	Cooper
164	José	Ferrer
165	Arthur	Kennedy
166	Fredric	March
167	Louis	Calhern
168	Broderick	Crawford
169	Richard	Todd
170	Lew	Ayres
171	Dan	Dailey
172	Clifton	Webb
173	Ronald	Colman
174	John	Garfield
175	William	Powell
176	Michael	Redgrave
177	Larry	Parks
178	Gene	Kelly
179	Ray	Milland
180	Cornel	Wilde
181	Barry	Fitzgerald
182	Cary	Grant
183	Alexander	Knox
184	Paul	Lukas
185	Walter	Pidgeon
186	Mickey	Rooney
187	Monty	Woolley
188	Walter	Huston
189	Robert	Montgomery
190	Orson	Welles
191	Charles	Chaplin
192	Raymond	Massey
193	Robert	Donat
194	Clark	Gable
195	Leslie	Howard
196	Victor	McLaglen
197	Franchot	Tone
198	Frank	Morgan
199	Wallace	Beery
200	Alfred	Lunt
201	Lionel	Barrymore
202	Jackie	Cooper
203	Richard	Dix
204	Adolphe	Menjou
205	George	Arliss
206	Maurice	Chevalier
207	Lawrence	Tibbett
208	George	Bancroft
209	Warner	Baxter
210	Chester	Morris
211	Lewis	Stone
212	Richard	Barthelmess
213	Emil	Jannings
214	Christian	Bale
215	John	Hawkes
216	Mark	Ruffalo
217	Christopher	Plummer
218	Stanley	Tucci
219	Christoph	Waltz
220	Josh	Brolin
221	Robert	Downey Jr.
222	Michael	Shannon
223	Casey	Affleck
224	Hal	Holbrook
225	Jackie	Earle Haley
226	Djimon	Hounsou
227	Eddie	Murphy
228	Mark	Wahlberg
229	Matt	Dillon
230	Paul	Giamatti
231	Jake	Gyllenhaal
232	Alan	Alda
233	Thomas	Haden Church
234	Clive	Owen
235	Alec	Baldwin
236	Benicio	Del Toro
237	Tim	Robbins
238	Ken	Watanabe
239	Chris	Cooper
240	John	C. Reilly
241	Christopher	Walken
242	Jim	Broadbent
243	Ethan	Hawke
244	Willem	Dafoe
245	Michael	Clarke Duncan
246	Haley	Joel Osment
247	James	Coburn
248	Robert	Forster
249	Greg	Kinnear
250	Burt	Reynolds
251	Cuba	Gooding, Jr.
252	William	H. Macy
253	Armin	Mueller-Stahl
254	James	Cromwell
255	Tim	Roth
256	Samuel	L. Jackson
257	Martin	Landau
258	Chazz	Palminteri
259	Gary	Sinise
260	John	Malkovich
261	Pete	Postlethwaite
262	Jaye	Davidson
263	David	Paymer
264	Harvey	Keitel
265	Michael	Lerner
266	Jack	Palance
267	Bruce	Davison
268	Andy	Garcia
269	Graham	Greene
270	Joe	Pesci
271	Danny	Aiello
272	Dan	Aykroyd
273	Kevin	Kline
274	River	Phoenix
275	Dean	Stockwell
276	Albert	Brooks
277	Sean	Connery
278	Vincent	Gardenia
279	Tom	Berenger
280	Denholm	Elliott
281	Dennis	Hopper
282	Don	Ameche
283	Klaus	Maria Brandauer
284	William	Hickey
285	Robert	Loggia
286	Eric	Roberts
287	Adolph	Caesar
288	Noriyuki	'Pat' Morita
289	Haing	S. Ngor
290	Ralph	Richardson
291	Charles	Durning
292	John	Lithgow
293	Sam	Shepard
294	Rip	Torn
295	Louis	Gossett, Jr.
296	Robert	Preston
297	James	Coco
298	John	Gielgud
299	Ian	Holm
300	Howard	E. Rollins, Jr.
301	Judd	Hirsch
302	Timothy	Hutton
303	Michael	O'Keefe
304	Jason	Robards
305	Frederic	Forrest
306	Justin	Henry
307	Bruce	Dern
308	Jack	Warden
309	Mikhail	Baryshnikov
310	Peter	Firth
311	Ned	Beatty
312	Burgess	Meredith
313	Burt	Young
314	George	Burns
315	Brad	Dourif
316	Chris	Sarandon
317	Fred	Astaire
318	Michael	V. Gazzo
319	Lee	Strasberg
320	Jack	Gilford
321	John	Houseman
322	Jason	Miller
323	Randy	Quaid
324	Eddie	Albert
325	James	Caan
326	Joel	Grey
327	Leonard	Frey
328	Richard	Jaeckel
329	Ben	Johnson
330	Richard	Castellano
331	Chief	Dan George
332	John	Marley
333	John	Mills
334	Rupert	Crosse
335	Elliott	Gould
336	Anthony	Quayle
337	Gig	Young
338	Jack	Albertson
339	Seymour	Cassel
340	Daniel	Massey
341	Jack	Wild
342	Gene	Wilder
343	John	Cassavetes
344	Cecil	Kellaway
345	George	Kennedy
346	Michael	J. Pollard
347	Mako	
348	George	Segal
349	Robert	Shaw
350	Martin	Balsam
351	Ian	Bannen
352	Michael	Dunn
353	Frank	Finlay
354	Stanley	Holloway
355	Edmond	O'Brien
356	Lee	Tracy
357	Peter	Ustinov
358	Nick	Adams
359	Bobby	Darin
360	Hugh	Griffith
361	John	Huston
362	Ed	Begley
363	Victor	Buono
364	Telly	Savalas
365	Omar	Sharif
366	Terence	Stamp
367	George	Chakiris
368	Peter	Falk
369	Jackie	Gleason
370	Jack	Kruschen
371	Sal	Mineo
372	Chill	Wills
373	Arthur	O'Connell
374	Robert	Vaughn
375	Ed	Wynn
376	Theodore	Bikel
377	Lee	J. Cobb
378	Burl	Ives
379	Red	Buttons
380	Vittorio	De Sica
381	Sessue	Hayakawa
382	Russ	Tamblyn
383	Don	Murray
384	Anthony	Perkins
385	Robert	Stack
386	Joe	Mantell
387	Karl	Malden
388	Tom	Tully
389	Brandon	De Wilde
390	Robert	Strauss
391	Arthur	Hunnicutt
392	Leo	Genn
393	Kevin	McCarthy
394	Jeff	Chandler
395	Edmund	Gwenn
396	Sam	Jaffe
397	George	Sanders
398	Erich	von Stroheim
399	John	Ireland
400	Dean	Jagger
401	Charles	Bickford
402	Oscar	Homolka
403	Thomas	Gomez
404	Robert	Ryan
405	Richard	Widmark
406	Charles	Coburn
407	William	Demarest
408	Claude	Rains
409	Harold	Russell
410	Michael	Chekhov
411	John	Dall
412	James	Dunn
413	Robert	Mitchum
414	J.	Carrol Naish
415	Hume	Cronyn
416	Akim	Tamiroff
417	William	Bendix
418	Van	Heflin
419	Henry	Travers
420	Walter	Brennan
421	Donald	Crisp
422	James	Gleason
423	Sydney	Greenstreet
424	Albert	Basserman
425	William	Gargan
426	Jack	Oakie
427	James	Stephenson
428	Brian	Aherne
429	Harry	Carey
430	Brian	Donlevy
431	Thomas	Mitchell
432	Gene	Lockhart
433	Robert	Morley
434	Basil	Rathbone
435	Ralph	Bellamy
436	Joseph	Schildkraut
437	H.	B. Warner
438	Roland	Young
439	Mischa	Auer
440	Stuart	Erwin
441	Annette	Bening
442	Nicole	Kidman
443	Jennifer	Lawrence
444	Natalie	Portman
445	Michelle	Williams
446	Sandra	Bullock
447	Helen	Mirren
448	Carey	Mulligan
449	Gabourey	Sidibe
450	Meryl	Streep
451	Anne	Hathaway
452	Angelina	Jolie
453	Melissa	Leo
454	Kate	Winslet
455	Cate	Blanchett
456	Julie	Christie
457	Marion	Cotillard
458	Laura	Linney
459	Ellen	Page
460	Penélope	Cruz
461	Judi	Dench
462	Felicity	Huffman
463	Keira	Knightley
464	Charlize	Theron
465	Reese	Witherspoon
466	Catalina	Sandino Moreno
467	Imelda	Staunton
468	Hilary	Swank
469	Keisha	Castle-Hughes
470	Diane	Keaton
471	Samantha	Morton
472	Naomi	Watts
473	Salma	Hayek
474	Diane	Lane
475	Julianne	Moore
476	Renée	Zellweger
477	Halle	Berry
478	Sissy	Spacek
479	Joan	Allen
480	Juliette	Binoche
481	Ellen	Burstyn
482	Julia	Roberts
483	Janet	McTeer
484	Fernanda	Montenegro
485	Gwyneth	Paltrow
486	Emily	Watson
487	Helena	Bonham Carter
488	Helen	Hunt
489	Brenda	Blethyn
490	Frances	McDormand
491	Kristin	Scott Thomas
492	Susan	Sarandon
493	Elisabeth	Shue
494	Sharon	Stone
495	Emma	Thompson
496	Jodie	Foster
497	Jessica	Lange
498	Miranda	Richardson
499	Winona	Ryder
500	Angela	Bassett
501	Stockard	Channing
502	Holly	Hunter
503	Debra	Winger
504	Catherine	Deneuve
505	Mary	McDonnell
506	Michelle	Pfeiffer
507	Geena	Davis
508	Laura	Dern
509	Bette	Midler
510	Kathy	Bates
511	Anjelica	Huston
512	Joanne	Woodward
513	Isabelle	Adjani
514	Pauline	Collins
515	Jessica	Tandy
516	Glenn	Close
517	Melanie	Griffith
518	Sigourney	Weaver
519	Cher	
520	Sally	Kirkland
521	Jane	Fonda
522	Marlee	Matlin
523	Kathleen	Turner
524	Anne	Bancroft
525	Whoopi	Goldberg
526	Geraldine	Page
527	Judy	Davis
528	Sally	Field
529	Vanessa	Redgrave
530	Jane	Alexander
531	Shirley	MacLaine
532	Julie	Walters
533	Julie	Andrews
534	Katharine	Hepburn
535	Marsha	Mason
536	Goldie	Hawn
537	Mary	Tyler Moore
538	Gena	Rowlands
539	Jill	Clayburgh
540	Ingrid	Bergman
541	Marie-Christine	Barrault
542	Faye	Dunaway
543	Talia	Shire
544	Liv	Ullmann
545	Ann-Margret	
546	Louise	Fletcher
547	Glenda	Jackson
548	Carol	Kane
549	Diahann	Carroll
550	Valerie	Perrine
551	Barbra	Streisand
552	Liza	Minnelli
553	Diana	Ross
554	Maggie	Smith
555	Cicely	Tyson
556	Janet	Suzman
557	Ali	MacGraw
558	Sarah	Miles
559	Carrie	Snodgress
560	Genevieve	Bujold
561	Jean	Simmons
562	Patricia	Neal
563	Dame	Edith Evans
564	Audrey	Hepburn
565	Anouk	Aimee
566	Ida	Kaminska
567	Lynn	Redgrave
568	Elizabeth	Taylor
569	Samantha	Eggar
570	Elizabeth	Hartman
571	Simone	Signoret
572	Sophia	Loren
573	Debbie	Reynolds
574	Kim	Stanley
575	Leslie	Caron
576	Rachel	Roberts
577	Natalie	Wood
578	Bette	Davis
579	Lee	Remick
580	Piper	Laurie
581	Greer	Garson
582	Deborah	Kerr
583	Melina	Mercouri
584	Doris	Day
585	Susan	Hayward
586	Rosalind	Russell
587	Anna	Magnani
588	Lana	Turner
589	Carroll	Baker
590	Nancy	Kelly
591	Jennifer	Jones
592	Eleanor	Parker
593	Dorothy	Dandridge
594	Judy	Garland
595	Grace	Kelly
596	Jane	Wyman
597	Ava	Gardner
598	Maggie	McNamara
599	Shirley	Booth
600	Joan	Crawford
601	Julie	Harris
602	Vivien	Leigh
603	Shelley	Winters
604	Anne	Baxter
605	Judy	Holliday
606	Gloria	Swanson
607	Jeanne	Crain
608	Olivia	de Havilland
609	Loretta	Young
610	Irene	Dunne
611	Barbara	Stanwyck
612	Dorothy	McGuire
613	Celia	Johnson
614	Gene	Tierney
615	Claudette	Colbert
616	Jean	Arthur
617	Joan	Fontaine
618	Teresa	Wright
619	Ginger	Rogers
620	Martha	Scott
621	Greta	Garbo
622	Fay	Bainter
623	Wendy	Hiller
624	Norma	Shearer
625	Margaret	Sullavan
626	Janet	Gaynor
627	Luise	Rainer
628	Gladys	George
629	Carole	Lombard
630	Elisabeth	Bergner
631	Miriam	Hopkins
632	Merle	Oberon
633	Grace	Moore
634	May	Robson
635	Diana	Wynyard
636	Marie	Dressler
637	Lynn	Fontanne
638	Helen	Hayes
639	Marlene	Dietrich
640	Ann	Harding
641	Nancy	Carroll
642	Ruth	Chatterton
643	Betty	Compson
644	Jeanne	Eagels
645	Corinne	Griffith
646	Bessie	Love
647	Mary	Pickford
648	Louise	Dresser
649	Amy	Adams
650	Hailee	Steinfeld
651	Jacki	Weaver
652	Vera	Farmiga
653	Maggie	Gyllenhaal
654	Anna	Kendrick
655	Mo'Nique	
656	Viola	Davis
657	Taraji	P. Henson
658	Marisa	Tomei
659	Ruby	Dee
660	Saoirse	Ronan
661	Amy	Ryan
662	Tilda	Swinton
663	Adriana	Barraza
664	Abigail	Breslin
665	Jennifer	Hudson
666	Rinko	Kikuchi
667	Catherine	Keener
668	Rachel	Weisz
669	Virginia	Madsen
670	Sophie	Okonedo
671	Shohreh	Aghdashloo
672	Patricia	Clarkson
673	Marcia	Gay Harden
674	Queen	Latifah
675	Catherine	Zeta-Jones
676	Jennifer	Connelly
677	Kate	Hudson
678	Toni	Collette
679	Chloë	Sevigny
680	Rachel	Griffiths
681	Kim	Basinger
682	Joan	Cusack
683	Minnie	Driver
684	Gloria	Stuart
685	Lauren	Bacall
686	Barbara	Hershey
687	Marianne	Jean-Baptiste
688	Kathleen	Quinlan
689	Mira	Sorvino
690	Mare	Winningham
691	Rosemary	Harris
692	Uma	Thurman
693	Jennifer	Tilly
694	Dianne	Wiest
695	Anna	Paquin
696	Rosie	Perez
697	Joan	Plowright
698	Diane	Ladd
699	Juliette	Lewis
700	Kate	Nelligan
701	Mercedes	Ruehl
702	Lorraine	Bracco
703	Brenda	Fricker
704	Lena	Olin
705	Norma	Aleandro
706	Anne	Archer
707	Olympia	Dukakis
708	Anne	Ramsey
709	Ann	Sothern
710	Tess	Harper
711	Mary	Elizabeth Mastrantonio
712	Margaret	Avery
713	Amy	Madigan
714	Meg	Tilly
715	Oprah	Winfrey
716	Peggy	Ashcroft
717	Lindsay	Crouse
718	Christine	Lahti
719	Linda	Hunt
720	Amy	Irving
721	Alfre	Woodard
722	Teri	Garr
723	Lesley	Ann Warren
724	Melinda	Dillon
725	Joan	Hackett
726	Elizabeth	McGovern
727	Maureen	Stapleton
728	Eileen	Brennan
729	Eva	Le Gallienne
730	Cathy	Moriarty
731	Diana	Scarwid
732	Mary	Steenburgen
733	Barbara	Barrie
734	Candice	Bergen
735	Mariel	Hemingway
736	Dyan	Cannon
737	Penelope	Milford
738	Leslie	Browne
739	Quinn	Cummings
740	Tuesday	Weld
741	Lee	Grant
742	Beatrice	Straight
743	Ronee	Blakley
744	Sylvia	Miles
745	Lily	Tomlin
746	Brenda	Vaccaro
747	Valentina	Cortese
748	Madeline	Kahn
749	Linda	Blair
750	Candy	Clark
751	Tatum	O'Neal
752	Sylvia	Sidney
753	Jeannie	Berlin
754	Eileen	Heckart
755	Susan	Tyrrell
756	Barbara	Harris
757	Cloris	Leachman
758	Margaret	Leighton
759	Karen	Black
760	Sally	Kellerman
761	Catherine	Burns
762	Susannah	York
763	Lynn	Carlin
764	Ruth	Gordon
765	Sondra	Locke
766	Kay	Medford
767	Estelle	Parsons
768	Carol	Channing
769	Mildred	Natwick
770	Beah	Richards
771	Katharine	Ross
772	Sandy	Dennis
773	Jocelyne	Lagarde
774	Vivien	Merchant
775	Joyce	Redman
776	Peggy	Wood
777	Gladys	Cooper
778	Grayson	Hall
779	Lila	Kedrova
780	Agnes	Moorehead
781	Diane	Cilento
782	Margaret	Rutherford
783	Lilia	Skala
784	Mary	Badham
785	Patty	Duke
786	Shirley	Knight
787	Angela	Lansbury
788	Thelma	Ritter
789	Lotte	Lenya
790	Una	Merkel
791	Rita	Moreno
792	Glynis	Johns
793	Shirley	Jones
794	Janet	Leigh
795	Mary	Ure
796	Hermione	Baddeley
797	Susan	Kohner
798	Juanita	Moore
799	Peggy	Cass
800	Martha	Hyer
801	Cara	Williams
802	Carolyn	Jones
803	Elsa	Lanchester
804	Hope	Lange
805	Miyoshi	Umeki
806	Diane	Varsi
807	Mildred	Dunnock
808	Mercedes	McCambridge
809	Patty	McCormack
810	Dorothy	Malone
811	Betsy	Blair
812	Peggy	Lee
813	Marisa	Pavan
814	Jo	Van Fleet
815	Nina	Foch
816	Katy	Jurado
817	Eva	Marie Saint
818	Jan	Sterling
819	Claire	Trevor
820	Marjorie	Rambeau
821	Donna	Reed
822	Gloria	Grahame
823	Jean	Hagen
824	Colette	Marchand
825	Terry	Moore
826	Joan	Blondell
827	Kim	Hunter
828	Hope	Emerson
829	Celeste	Holm
830	Josephine	Hull
831	Nancy	Olson
832	Ethel	Barrymore
833	Ethel	Waters
834	Barbara	Bel Geddes
835	Ellen	Corby
836	Marjorie	Main
837	Anne	Revere
838	Lillian	Gish
839	Flora	Robson
840	Gale	Sondergaard
841	Eve	Arden
842	Ann	Blyth
843	Joan	Lorring
844	Aline	MacMahon
845	Paulette	Goddard
846	Katina	Paxinou
847	Lucile	Watson
848	Susan	Peters
849	Dame	May Whitty
850	Sara	Allgood
851	Mary	Astor
852	Patricia	Collinge
853	Margaret	Wycherly
854	Judith	Anderson
855	Jane	Darwell
856	Ruth	Hussey
857	Barbara	O'Neil
858	Geraldine	Fitzgerald
859	Hattie	McDaniel
860	Edna	May Oliver
861	Maria	Ouspenskaya
862	Beulah	Bondi
863	Billie	Burke
864	Spring	Byington
865	Miliza	Korjus
866	Alice	Brady
867	Andrea	Leeds
868	Anne	Shirley
869	Bonita	Granville
\.


--
-- Name: people_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('people_id_seq', 869, true);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: cities_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cities
    ADD CONSTRAINT cities_pkey PRIMARY KEY (id);


--
-- Name: games_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY games
    ADD CONSTRAINT games_pkey PRIMARY KEY (id);


--
-- Name: nominations_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY nominations
    ADD CONSTRAINT nominations_pkey PRIMARY KEY (id);


--
-- Name: people_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY people
    ADD CONSTRAINT people_pkey PRIMARY KEY (id);


--
-- Name: index_nominations_on_category_id; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX index_nominations_on_category_id ON nominations USING btree (category_id);


--
-- Name: index_nominations_on_person_id; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX index_nominations_on_person_id ON nominations USING btree (person_id);


--
-- Name: fk_rails_1d03cd273a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY nominations
    ADD CONSTRAINT fk_rails_1d03cd273a FOREIGN KEY (category_id) REFERENCES categories(id);


--
-- Name: fk_rails_a176e00663; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY nominations
    ADD CONSTRAINT fk_rails_a176e00663 FOREIGN KEY (person_id) REFERENCES people(id);


--
-- PostgreSQL database dump complete
--

