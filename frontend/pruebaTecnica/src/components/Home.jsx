import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Home = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    // Obtener la lista de posts al cargar el componente
    fetchPosts();
  }, []);

  const fetchPosts = async () => {
    try {
      const response = await axios.get("localhost:8080/posts");
      setPosts(response.data);
    } catch (error) {
      console.error('Error al obtener los posts', error);
    }
  };

  return (
    <div>
      <h1>Bienvenido a la Página Principal</h1>
      <h2>Últimos Posts:</h2>
      <ul>
        {posts.map(post => (
          <li key={post.id}>
            <strong>{post.titulo}</strong>
            <p>{post.descripcion}</p>
            {/* Puedes mostrar la imagen aquí si lo deseas */}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Home;