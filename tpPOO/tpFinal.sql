PGDMP     .                
    z            tpFinal    12.4    12.4 
               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            
           1262    34655    tpFinal    DATABASE     �   CREATE DATABASE "tpFinal" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Argentina.1252' LC_CTYPE = 'Spanish_Argentina.1252';
    DROP DATABASE "tpFinal";
                postgres    false            �            1259    34952    empleado    TABLE       CREATE TABLE public.empleado (
    n_legajo integer NOT NULL,
    apellido character varying(50) NOT NULL,
    cod_proyecto integer NOT NULL,
    dni integer NOT NULL,
    fecha_nacimiento date NOT NULL,
    nombre character varying(50) NOT NULL,
    sueldo real NOT NULL
);
    DROP TABLE public.empleado;
       public         heap    postgres    false            �            1259    34957    proyecto    TABLE     �   CREATE TABLE public.proyecto (
    cod_proyecto integer NOT NULL,
    fecha_finalizacion date NOT NULL,
    fecha_inicio date NOT NULL,
    monto real NOT NULL,
    nom_proyecto character varying(100) NOT NULL
);
    DROP TABLE public.proyecto;
       public         heap    postgres    false                      0    34952    empleado 
   TABLE DATA           k   COPY public.empleado (n_legajo, apellido, cod_proyecto, dni, fecha_nacimiento, nombre, sueldo) FROM stdin;
    public          postgres    false    202   �
                 0    34957    proyecto 
   TABLE DATA           g   COPY public.proyecto (cod_proyecto, fecha_finalizacion, fecha_inicio, monto, nom_proyecto) FROM stdin;
    public          postgres    false    203   ^       �
           2606    34956    empleado empleado_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (n_legajo);
 @   ALTER TABLE ONLY public.empleado DROP CONSTRAINT empleado_pkey;
       public            postgres    false    202            �
           2606    34961    proyecto proyecto_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (cod_proyecto);
 @   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT proyecto_pkey;
       public            postgres    false    203               r  x�E�Kk�0�������ÏcŗZR
�^�Z-�n�8����ZJ:�b�i4���~��n	���@%��]�L~��$U^\�B�-bZ
ih��M���5�6�����m��Ev��c�[�p��^m��B�Bvp�&WЎ������6�В�_@��E�~=�*��1[ܖ���i������2W�9nͶ��q���.��f��-n��V5���ᮕ!<�)Qe���iA��S���������z-]OjA[���Ը�v�.�(J���ZH:���G��|�����ʭ��,�΍��׺��T��jN�I8'ٔ��F��nr1���.æ�a�+���r���ηg��:Cy,�Ϳ�mP���˼��c�         �   x�E��
�@�继�.'�Ԗ:�	n�.�5ÁMʵ:��6Eh��C��:�D)�#n��k��~Xxȅ%��5�z�x���qӁ��o�E��&�'��f7�{�+F^Ṯ��F�B�6`2�17�-�͍�!b��o�iN0U��GG��p{9f     