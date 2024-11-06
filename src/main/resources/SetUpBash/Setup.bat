#!/bin/bash

# Function to get user input
get_input() {
  echo -n "$1: "
  read input
  echo $input
}

# 1. Change the root password of the system
echo "Changing root password for the system..."
new_system_root_password=$(get_input "Enter new root password for the system")

# Change the root password on the server
echo "root:$new_system_root_password" | sudo chpasswd

# 2. Create a new user with root access on the system
new_system_user=$(get_input "Enter new username for the server")
new_system_user_password=$(get_input "Enter password for the new user on the server")

# Create new user
sudo useradd -m -s /bin/bash $new_system_user
echo "$new_system_user:$new_system_user_password" | sudo chpasswd
sudo usermod -aG sudo $new_system_user  # Add the new user to the sudo group

# 3. Install MySQL
echo "Installing MySQL..."
sudo apt-get update
sudo apt-get install -y mysql-server

# 4. Change the root password for MySQL
root_password=$(get_input "Enter new root password for MySQL")
sudo mysql -e "ALTER USER 'root'@'localhost' IDENTIFIED BY '$root_password';"

# 5. Create a new user in MySQL with root privileges
new_user=$(get_input "Enter new MySQL username")
new_password=$(get_input "Enter password for the new MySQL user")
new_host=$(get_input "Enter IP address or '%' for remote access (use '%' for any IP)")

# Create new MySQL user and grant root privileges
sudo mysql -e "CREATE USER '$new_user'@'$new_host' IDENTIFIED BY '$new_password';"
sudo mysql -e "GRANT ALL PRIVILEGES ON *.* TO '$new_user'@'$new_host' WITH GRANT OPTION;"
sudo mysql -e "FLUSH PRIVILEGES;"

# 6. Allow MySQL to listen on all IPs for remote access
echo "Configuring MySQL for remote access..."
sudo sed -i "s/^bind-address\s*=.*$/bind-address = 0.0.0.0/" /etc/mysql/mysql.conf.d/mysqld.cnf

# Restart MySQL service to apply changes
sudo systemctl restart mysql

# 7. Configure firewall to allow MySQL port (3306) for remote access
echo "Configuring firewall for MySQL remote access..."
# Allow MySQL port 3306 through the firewall for any IP (change if necessary)
sudo ufw allow 3306

# Reload firewall to apply changes
sudo ufw reload

# Summary of actions
echo "Root password for the system has been changed to '$new_system_root_password'."
echo "New user '$new_system_user' with password '$new_system_user_password' and sudo privileges has been created."
echo "Root password for MySQL has been changed to '$root_password'."
echo "New MySQL user '$new_user' with remote access and password '$new_password' has been created."
echo "MySQL is now configured to accept remote connections."